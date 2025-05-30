import networkx as nx
import matplotlib.pyplot as plt
import matplotlib.cm as cm
from collections import deque
import numpy as np


def parse_adjacency_list(adjacency_text):
    """
    Parse adjacency list format into a NetworkX graph.

    Args:
        adjacency_text (str): Multi-line string where each line is in format:
                             "node neighbor1 neighbor2 ..."

    Returns:
        networkx.Graph: Undirected graph representing the maze
    """
    G = nx.Graph()

    # Split into lines and process each line
    lines = adjacency_text.strip().split("\n")

    for line in lines:
        if not line.strip():  # Skip empty lines
            continue

        # Split the line into numbers
        numbers = line.strip().split()

        if len(numbers) == 0:
            continue

        # First number is the source node
        source = int(numbers[0])

        # Add the source node to the graph (in case it has no neighbors)
        G.add_node(source)

        # Remaining numbers are neighbors
        for i in range(1, len(numbers)):
            neighbor = int(numbers[i])
            G.add_edge(source, neighbor)

    return G


def grid_layout(G, grid_width=None):
    """
    Create a grid layout for maze nodes.
    Assumes nodes are numbered sequentially starting from 1.

    Args:
        G (networkx.Graph): The maze graph
        grid_width (int): Width of the original grid. If None, tries to infer it.

    Returns:
        dict: Position dictionary for NetworkX drawing
    """
    nodes = list(G.nodes())
    max_node = max(nodes)

    # Try to infer grid dimensions if not provided
    if grid_width is None:
        # Common approach: try to find a square-ish grid
        import math

        grid_width = int(math.ceil(math.sqrt(max_node)))

    pos = {}
    for node in nodes:
        # Convert 1-based node number to 0-based for calculation
        node_0_based = node - 1
        x = node_0_based % grid_width
        y = node_0_based // grid_width
        # Flip y to match typical maze representation (origin at top-left)
        pos[node] = (x, -y)

    return pos


def tree_layout(G, root, horizontal_spacing=1.0, vertical_spacing=1.0):
    """
    Create a tree layout based on BFS levels from a root node.

    Args:
        G (networkx.Graph): The graph
        root: Root node for the tree layout
        horizontal_spacing (float): Spacing between nodes at same level
        vertical_spacing (float): Spacing between levels

    Returns:
        dict: Position dictionary for NetworkX drawing
    """
    if root not in G.nodes():
        raise ValueError(f"Root node {root} not in graph")

    # Get BFS tree structure
    bfs_result = bfs_traversal(G, root)
    distances = bfs_result["distances"]

    # Group nodes by their BFS level
    levels = {}
    for node, dist in distances.items():
        if dist not in levels:
            levels[dist] = []
        levels[dist].append(node)

    pos = {}

    # Position nodes level by level
    for level, nodes in levels.items():
        y = -level * vertical_spacing  # Negative so tree grows downward

        # Center the nodes horizontally at this level
        num_nodes = len(nodes)
        if num_nodes == 1:
            x_positions = [0]
        else:
            # Spread nodes evenly across the level
            total_width = (num_nodes - 1) * horizontal_spacing
            start_x = -total_width / 2
            x_positions = [start_x + i * horizontal_spacing for i in range(num_nodes)]

        # Assign positions
        for i, node in enumerate(sorted(nodes)):  # Sort for consistency
            pos[node] = (x_positions[i], y)

    return pos


def hierarchical_tree_layout(G, root, width=3.0):
    """
    Create a more sophisticated tree layout that tries to minimize edge crossings.

    Args:
        G (networkx.Graph): The graph
        root: Root node for the tree layout
        width (float): Total width available for the tree

    Returns:
        dict: Position dictionary for NetworkX drawing
    """
    if root not in G.nodes():
        raise ValueError(f"Root node {root} not in graph")

    # Get BFS tree structure
    bfs_result = bfs_traversal(G, root)
    bfs_tree = bfs_result["bfs_tree"]
    distances = bfs_result["distances"]

    # Group nodes by level
    levels = {}
    for node, dist in distances.items():
        if dist not in levels:
            levels[dist] = []
        levels[dist].append(node)

    pos = {}

    # Position root
    pos[root] = (0, 0)

    # For each level, position children under their parents
    for level in sorted(levels.keys())[1:]:  # Skip level 0 (root)
        nodes_at_level = levels[level]
        y = -level  # Tree grows downward

        # Group children by their parents
        parent_groups = {}
        for node in nodes_at_level:
            # Find parent in BFS tree
            parent = None
            for edge in bfs_result["bfs_tree_edges"]:
                if edge[1] == node:  # node is the child
                    parent = edge[0]
                    break

            if parent is not None:
                if parent not in parent_groups:
                    parent_groups[parent] = []
                parent_groups[parent].append(node)

        # Position children under each parent
        x_offset = 0
        for parent in sorted(parent_groups.keys()):
            children = sorted(parent_groups[parent])
            parent_x = pos[parent][0]

            if len(children) == 1:
                pos[children[0]] = (parent_x, y)
            else:
                # Spread children around parent
                child_width = len(children) * 0.8
                start_x = parent_x - child_width / 2
                for i, child in enumerate(children):
                    child_x = start_x + i * 0.8
                    pos[child] = (child_x, y)

    return pos
    """
    Create a grid layout for maze nodes.
    Assumes nodes are numbered sequentially starting from 1.

    Args:
        G (networkx.Graph): The maze graph
        grid_width (int): Width of the original grid. If None, tries to infer it.

    Returns:
        dict: Position dictionary for NetworkX drawing
    """
    nodes = list(G.nodes())
    max_node = max(nodes)

    # Try to infer grid dimensions if not provided
    if grid_width is None:
        # Common approach: try to find a square-ish grid
        import math

        grid_width = int(math.ceil(math.sqrt(max_node)))

    pos = {}
    for node in nodes:
        # Convert 1-based node number to 0-based for calculation
        node_0_based = node - 1
        x = node_0_based % grid_width
        y = node_0_based // grid_width
        # Flip y to match typical maze representation (origin at top-left)
        pos[node] = (x, -y)

    return pos


def visualize_maze_graph(
    G, layout_type="spring", save_path=None, grid_width=None, start_node=1
):
    """
    Visualize the maze graph using matplotlib.

    Args:
        G (networkx.Graph): The maze graph
        layout_type (str): Layout algorithm ('spring', 'circular', 'random', 'grid', etc.)
        save_path (str): Optional path to save the figure (e.g., 'maze.png')
        grid_width (int): Width of original grid (only used for 'grid' layout)
    """
    plt.figure(figsize=(12, 8))

    # Choose layout
    if layout_type == "spring":
        pos = nx.spring_layout(G, k=1, iterations=50)
    elif layout_type == "circular":
        pos = nx.circular_layout(G)
    elif layout_type == "random":
        pos = nx.random_layout(G)
    elif layout_type == "kamada_kawai":
        pos = nx.kamada_kawai_layout(G)
    elif layout_type == "spectral":
        pos = nx.spectral_layout(G)
    elif layout_type == "shell":
        pos = nx.shell_layout(G)
    elif layout_type == "spiral":
        pos = nx.spiral_layout(G)
    elif layout_type == "grid":
        pos = grid_layout(G, grid_width)
    elif layout_type == "tree":
        pos = tree_layout(G, start_node)
    elif layout_type == "hierarchical_tree":
        pos = hierarchical_tree_layout(G, start_node)
    else:
        pos = nx.spring_layout(G)

    # Draw the graph
    nx.draw(
        G,
        pos,
        with_labels=True,
        node_color="lightblue",
        node_size=300,
        font_size=8,
        font_weight="bold",
        edge_color="gray",
        width=1,
    )

    plt.title(f"Maze Graph Visualization ({layout_type} layout)")
    plt.axis("off")
    plt.tight_layout()

    # Save if path provided
    if save_path:
        plt.savefig(
            save_path, dpi=300, bbox_inches="tight", facecolor="white", edgecolor="none"
        )
        print(f"Graph saved to: {save_path}")

    plt.show()


def bfs_traversal(G, start_node):
    """
    Perform BFS traversal and return detailed information about the search.

    Args:
        G (networkx.Graph): The maze graph
        start_node: Starting node for BFS

    Returns:
        dict: Contains BFS tree, distances, visit order, and edge depths
    """
    if start_node not in G.nodes():
        raise ValueError(f"Start node {start_node} not in graph")

    # BFS data structures
    visited = set()
    queue = deque([start_node])
    distances = {start_node: 0}
    parent = {start_node: None}
    visit_order = []
    bfs_tree_edges = []
    edge_depths = {}

    visited.add(start_node)
    visit_order.append(start_node)

    while queue:
        current = queue.popleft()
        current_depth = distances[current]

        # Explore neighbors
        for neighbor in G.neighbors(current):
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)
                distances[neighbor] = current_depth + 1
                parent[neighbor] = current
                visit_order.append(neighbor)

                # Record the BFS tree edge and its depth
                edge = (current, neighbor)
                bfs_tree_edges.append(edge)
                edge_depths[edge] = current_depth  # Depth of the parent node

    # Create BFS tree as a graph
    bfs_tree = nx.Graph()
    bfs_tree.add_edges_from(bfs_tree_edges)

    return {
        "bfs_tree": bfs_tree,
        "distances": distances,
        "parent": parent,
        "visit_order": visit_order,
        "bfs_tree_edges": bfs_tree_edges,
        "edge_depths": edge_depths,
        "max_depth": max(distances.values()) if distances else 0,
    }


def visualize_bfs(G, start_node, layout_type="grid", grid_width=None, save_path=None):
    """
    Visualize BFS traversal with edges colored by depth.

    Args:
        G (networkx.Graph): The maze graph
        start_node: Starting node for BFS
        layout_type (str): Layout algorithm
        grid_width (int): Width for grid layout
        save_path (str): Optional path to save the figure
    """
    # Perform BFS
    bfs_result = bfs_traversal(G, start_node)

    plt.figure(figsize=(15, 10))

    # Get layout positions
    if layout_type == "grid":
        pos = grid_layout(G, grid_width)
    elif layout_type == "spring":
        pos = nx.spring_layout(G, k=1, iterations=50)
    elif layout_type == "circular":
        pos = nx.circular_layout(G)
    else:
        pos = nx.spring_layout(G)

    # Color setup
    max_depth = bfs_result["max_depth"]
    cmap = plt.cm.get_cmap("viridis")  # Fix deprecated get_cmap usage

    # Draw all edges in light gray first (non-BFS edges)
    nx.draw_networkx_edges(G, pos, edge_color="lightgray", width=0.5, alpha=0.3)

    # Draw BFS tree edges with colors based on depth
    edge_colors = []
    edge_widths = []

    for edge in bfs_result["bfs_tree_edges"]:
        depth = bfs_result["edge_depths"][edge]
        # Normalize depth to [0, 1] for colormap
        normalized_depth = depth / max_depth if max_depth > 0 else 0
        edge_colors.append(cmap(normalized_depth))
        edge_widths.append(3)  # Thicker for BFS tree edges

    # Draw BFS tree edges
    nx.draw_networkx_edges(
        bfs_result["bfs_tree"], pos, edge_color=edge_colors, width=edge_widths
    )

    # Draw nodes with colors based on BFS distance
    node_colors = []
    for node in G.nodes():
        if node in bfs_result["distances"]:
            depth = bfs_result["distances"][node]
            normalized_depth = depth / max_depth if max_depth > 0 else 0
            node_colors.append(cmap(normalized_depth))
        else:
            node_colors.append("white")  # Unreachable nodes

    # Draw nodes
    nx.draw_networkx_nodes(
        G, pos, node_color=node_colors, node_size=400, edgecolors="black", linewidths=1
    )

    # Highlight start node
    nx.draw_networkx_nodes(
        G,
        pos,
        nodelist=[start_node],
        node_color="red",
        node_size=500,
        edgecolors="darkred",
        linewidths=2,
    )

    # Draw labels
    nx.draw_networkx_labels(G, pos, font_size=8, font_weight="bold")

    # Add colorbar - fix the axes issue
    sm = plt.cm.ScalarMappable(cmap=cmap, norm=plt.Normalize(vmin=0, vmax=max_depth))
    sm.set_array([])

    # Get current axes to attach colorbar
    ax = plt.gca()
    cbar = plt.colorbar(sm, ax=ax, shrink=0.8)
    cbar.set_label("BFS Depth", rotation=270, labelpad=15)

    plt.title(
        f"BFS Traversal from Node {start_node}\n"
        f'Max Depth: {max_depth}, Nodes Visited: {len(bfs_result["visit_order"])}'
    )
    plt.axis("off")
    plt.tight_layout()

    if save_path:
        plt.savefig(
            save_path, dpi=300, bbox_inches="tight", facecolor="white", edgecolor="none"
        )
        print(f"BFS visualization saved to: {save_path}")

    plt.show()

    return bfs_result


def visualize_bfs_animation_frames(
    G, start_node, layout_type="grid", grid_width=None, save_dir=None
):
    """
    Create multiple frames showing BFS progression step by step.

    Args:
        G (networkx.Graph): The maze graph
        start_node: Starting node for BFS
        layout_type (str): Layout algorithm
        grid_width (int): Width for grid layout
        save_dir (str): Directory to save frame images
    """
    bfs_result = bfs_traversal(G, start_node)
    visit_order = bfs_result["visit_order"]

    # Get layout positions
    if layout_type == "grid":
        pos = grid_layout(G, grid_width)
    else:
        pos = nx.spring_layout(G, k=1, iterations=50)

    frames = []

    # Create frame for each step of BFS
    for step in range(len(visit_order)):
        fig, ax = plt.subplots(figsize=(12, 8))

        # Nodes visited so far
        visited_so_far = set(visit_order[: step + 1])
        current_node = visit_order[step]

        # Draw all edges lightly
        nx.draw_networkx_edges(
            G, pos, edge_color="lightgray", width=0.5, alpha=0.3, ax=ax
        )

        # Draw visited nodes
        visited_nodes = list(visited_so_far)
        if visited_nodes:
            nx.draw_networkx_nodes(
                G,
                pos,
                nodelist=visited_nodes,
                node_color="lightblue",
                node_size=300,
                edgecolors="blue",
                linewidths=1,
                ax=ax,
            )

        # Highlight current node
        nx.draw_networkx_nodes(
            G,
            pos,
            nodelist=[current_node],
            node_color="red",
            node_size=400,
            edgecolors="darkred",
            linewidths=2,
            ax=ax,
        )

        # Draw start node distinctly
        if start_node != current_node:
            nx.draw_networkx_nodes(
                G,
                pos,
                nodelist=[start_node],
                node_color="green",
                node_size=400,
                edgecolors="darkgreen",
                linewidths=2,
                ax=ax,
            )

        # Draw unvisited nodes
        unvisited = [n for n in G.nodes() if n not in visited_so_far]
        if unvisited:
            nx.draw_networkx_nodes(
                G,
                pos,
                nodelist=unvisited,
                node_color="white",
                node_size=300,
                edgecolors="gray",
                linewidths=1,
                ax=ax,
            )

        # Draw labels
        nx.draw_networkx_labels(G, pos, font_size=8, font_weight="bold", ax=ax)

        ax.set_title(
            f"BFS Step {step + 1}: Visiting Node {current_node}\n"
            f'Distance from start: {bfs_result["distances"][current_node]}'
        )
        ax.axis("off")

        if save_dir:
            frame_path = f"{save_dir}/bfs_frame_{step:03d}.png"
            fig.savefig(frame_path, dpi=150, bbox_inches="tight")
            frames.append(frame_path)

        plt.tight_layout()
        plt.show()
        plt.close(fig)

    return frames


def print_bfs_results(bfs_result, start_node):
    """Print detailed BFS results."""
    print(f"\n=== BFS Results from Node {start_node} ===")
    print(f"Nodes visited: {len(bfs_result['visit_order'])}")
    print(f"Visit order: {bfs_result['visit_order']}")
    print(f"Maximum distance: {bfs_result['max_depth']}")

    print(f"\nDistances from start node:")
    for node, dist in sorted(bfs_result["distances"].items()):
        print(f"  Node {node}: distance {dist}")

    print(f"\nBFS Tree edges (parent → child):")
    for edge in bfs_result["bfs_tree_edges"]:
        parent, child = edge
        depth = bfs_result["edge_depths"][edge]
        print(f"  {parent} → {child} (depth {depth})")

    # Find farthest nodes
    max_dist = bfs_result["max_depth"]
    farthest_nodes = [
        node for node, dist in bfs_result["distances"].items() if dist == max_dist
    ]
    print(f"\nFarthest nodes from start (distance {max_dist}): {farthest_nodes}")


def analyze_maze(G):
    """
    Print basic analysis of the maze graph.

    Args:
        G (networkx.Graph): The maze graph
    """
    print(f"Number of nodes (rooms): {G.number_of_nodes()}")
    print(f"Number of edges (passages): {G.number_of_edges()}")
    print(f"Is connected: {nx.is_connected(G)}")

    if nx.is_connected(G):
        print(f"Diameter: {nx.diameter(G)}")
        print(f"Average shortest path length: {nx.average_shortest_path_length(G):.2f}")

    # Find nodes with different degrees
    degrees = dict(G.degree())
    max_degree = max(degrees.values())
    min_degree = min(degrees.values())

    print(f"Node degree range: {min_degree} to {max_degree}")

    # Find dead ends (degree 1)
    dead_ends = [node for node, degree in degrees.items() if degree == 1]
    print(f"Dead ends (degree 1): {len(dead_ends)} nodes")
    if dead_ends:
        print(f"Dead end nodes: {sorted(dead_ends)}")
    """
    Print basic analysis of the maze graph.

    Args:
        G (networkx.Graph): The maze graph
    """
    print(f"Number of nodes (rooms): {G.number_of_nodes()}")
    print(f"Number of edges (passages): {G.number_of_edges()}")
    print(f"Is connected: {nx.is_connected(G)}")

    if nx.is_connected(G):
        print(f"Diameter: {nx.diameter(G)}")
        print(f"Average shortest path length: {nx.average_shortest_path_length(G):.2f}")

    # Find nodes with different degrees
    degrees = dict(G.degree())
    max_degree = max(degrees.values())
    min_degree = min(degrees.values())

    print(f"Node degree range: {min_degree} to {max_degree}")

    # Find dead ends (degree 1)
    dead_ends = [node for node, degree in degrees.items() if degree == 1]
    print(f"Dead ends (degree 1): {len(dead_ends)} nodes")
    if dead_ends:
        print(f"Dead end nodes: {sorted(dead_ends)}")


# Example usage with your maze data
mazefile = open("maze_adj.txt", "r")
maze_data = mazefile.readlines()
maze_data = "".join(maze_data)  # Join lines into a single string
mazefile.close()

if __name__ == "__main__":
    # Parse the maze
    maze_graph = parse_adjacency_list(maze_data)

    # Analyze the maze
    print("=== Maze Analysis ===")
    analyze_maze(maze_graph)

    # Visualize the maze (and save it)
    print("\n=== Visualizing Maze ===")

    # Try different layouts including tree layouts
    print("Grid layout:")
    visualize_maze_graph(
        maze_graph, layout_type="grid", grid_width=6, save_path="maze_grid.png"
    )

    print("Tree layout:")
    visualize_maze_graph(
        maze_graph, layout_type="hierarchical_tree", save_path="maze_tree_2.png"
    )

    # BFS visualization
    print("\n=== BFS Traversal from Node 1 ===")
    bfs_result = visualize_bfs(
        maze_graph,
        start_node=1,
        layout_type="tree",
        save_path="bfs_tree_visualization.png",
    )

    # Print BFS results
    print_bfs_results(bfs_result, start_node=1)

    # Optional: Create animation frames (uncomment to use)
    # print("\n=== Creating BFS Animation Frames ===")
    # frames = visualize_bfs_animation_frames(maze_graph, start_node=1,
    #                                        layout_type='grid', grid_width=6,
    #                                        save_dir='bfs_frames')

    # You can also access the NetworkX graph directly for other operations
    print(f"\n=== NetworkX Graph Object ===")
    print(f"Type: {type(maze_graph)}")
    print(f"Nodes: {sorted(maze_graph.nodes())}")

    # Example: Find shortest path between two nodes
    if nx.is_connected(maze_graph):
        try:
            path = nx.shortest_path(maze_graph, source=1, target=42)
            print(f"Shortest path from node 1 to node 42: {path}")
            print(f"Path length: {len(path) - 1} steps")
        except nx.NetworkXNoPath:
            print("No path exists between nodes 1 and 42")
