#include <iostream>
#include <vector>
#include <cmath>
#include <unordered_map>
#include <utility>  // For std::pair

// Custom hash function for pairs (needed for unordered_map)
struct pair_hash {
    template <class T1, class T2>
    std::size_t operator()(const std::pair<T1, T2>& p) const {
        return std::hash<T1>()(p.first) ^ std::hash<T2>()(p.second);
    }
};

// Function to calculate entropy for a vector
double calculate_entropy(const std::vector<int>& data) {
    std::unordered_map<int, int> frequency;
    for (int value : data) {
        frequency[value]++;
    }

    double entropy = 0.0;
    int dataSize = data.size();
    for (const auto& entry : frequency) {
        double p = static_cast<double>(entry.second) / dataSize;
        entropy -= p * std::log2(p);
    }
    return entropy;
}

// Function to calculate joint entropy between two vectors
double calculate_joint_entropy(const std::vector<int>& x, const std::vector<int>& y) {
    std::unordered_map<std::pair<int, int>, int, pair_hash> joint_frequency;
    for (size_t i = 0; i < x.size(); ++i) {
        joint_frequency[{x[i], y[i]}]++;
    }

    double joint_entropy = 0.0;
    int dataSize = x.size();
    for (const auto& entry : joint_frequency) {
        double p = static_cast<double>(entry.second) / dataSize;
        joint_entropy -= p * std::log2(p);
    }
    return joint_entropy;
}

// Function to calculate Mutual Information (MI) score
double calculate_mutual_information(const std::vector<int>& x, const std::vector<int>& y) {
    double hx = calculate_entropy(x);
    double hy = calculate_entropy(y);
    double hxy = calculate_joint_entropy(x, y);
    return hx + hy - hxy;
}

// Function to compute MI matrix for a matrix
std::vector<std::vector<double>> compute_mi_matrix(const std::vector<std::vector<int>>& matrix) {
    size_t n = matrix.size();
    std::vector<std::vector<double>> mi_matrix(n, std::vector<double>(n, 0.0));

    for (size_t i = 0; i < n; ++i) {
        for (size_t j = i + 1; j < n; ++j) {
            mi_matrix[i][j] = calculate_mutual_information(matrix[i], matrix[j]);
            mi_matrix[j][i] = mi_matrix[i][j]; // Symmetric matrix
        }
    }
    return mi_matrix;
}

#include <iostream>
#include <vector>
#include <random>
#include <cstdlib>  // For atoi()

// (Include the other functions from the previous example here: pair_hash, calculate_entropy, calculate_joint_entropy, calculate_mutual_information, compute_mi_matrix)

int main(int argc, char* argv[]) {
    // Check for command-line argument
    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " <size of matrix (n)>\n";
        return 1;
    }

    // Read n from command-line argument
    int n = std::atoi(argv[1]);
    if (n <= 0) {
        std::cerr << "Matrix size must be a positive integer.\n";
        return 1;
    }

    // Read n from command-line argument
    int m = std::atoi(argv[1]);
    if (n <= 0) {
        std::cerr << "Matrix size must be a positive integer.\n";
        return 1;
    }

    // Initialize random number generator
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dist(0, 3); // Generates random integers between 0 and 3

    // Generate random n x n matrix with integer values
    std::vector<std::vector<int>> matrix(n, std::vector<int>(m));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            matrix[i][j] = dist(gen);
        }
    }

    // Calculate and display the MI matrix
		std::cout << "\nMutual Information Matrix Start:\n";

    std::vector<std::vector<double>> mi_matrix = compute_mi_matrix(matrix);
    std::cout << "\nMutual Information Matrix Done:\n";
    /**  for (const auto& row : mi_matrix) {
        for (double mi : row) {
            std::cout << mi << "\t";
        }
        std::cout << "\n";
    }
		**/

    return 0;
}
