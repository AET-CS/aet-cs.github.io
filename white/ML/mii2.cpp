#include <iostream>
#include <vector>
#include <thread>
#include <mutex>
#include <cmath>
#include <unordered_map>

#include <iostream>
#include <vector>
#include <random>
#include <cstdlib>  // For atoi()


// (Include the pair_hash, calculate_entropy, calculate_joint_entropy, calculate_mutual_information functions here)

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

// Multithreaded function to compute part of the MI matrix
void compute_mi_matrix_part(const std::vector<std::vector<int>>& matrix,
                            std::vector<std::vector<double>>& mi_matrix,
                            int start, int end) {
    int n = matrix.size();
    for (int i = start; i < end; ++i) {
        for (int j = i + 1; j < n; ++j) {
            mi_matrix[i][j] = calculate_mutual_information(matrix[i], matrix[j]);
            mi_matrix[j][i] = mi_matrix[i][j]; // Symmetric matrix
        }
    }
}

// Function to compute MI matrix using multiple threads
std::vector<std::vector<double>> compute_mi_matrix_multithreaded(const std::vector<std::vector<int>>& matrix, int num_threads) {
    int n = matrix.size();
    std::vector<std::vector<double>> mi_matrix(n, std::vector<double>(n, 0.0));

    // Determine the number of rows each thread should handle
    int rows_per_thread = n / num_threads;
    int extra_rows = n % num_threads;  // Some threads may need to process an extra row

    std::vector<std::thread> threads;
    int current_start = 0;

    for (int t = 0; t < num_threads; ++t) {
        int current_end = current_start + rows_per_thread + (t < extra_rows ? 1 : 0);
        threads.emplace_back(compute_mi_matrix_part, std::cref(matrix), std::ref(mi_matrix), current_start, current_end);
        current_start = current_end;
    }

    // Wait for all threads to complete
    for (std::thread& t : threads) {
        if (t.joinable()) {
            t.join();
        }
    }

    return mi_matrix;
}

// Main function to test MI calculation
int main(int argc, char* argv[]) {
    if (argc < 4) {
        std::cerr << "Usage: " << argv[0] << " <size of matrix (n)> <number of threads>\n";
        return 1;
    }

    int n = std::atoi(argv[1]);
    int m = std::atoi(argv[2]);
    int num_threads = std::atoi(argv[3]);

    if (n <= 0 || num_threads <= 0) {
        std::cerr << "Matrix size and number of threads must be positive integers.\n";
        return 1;
    }

    // Initialize random number generator and create a random matrix
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dist(0, 1);
    std::vector<std::vector<int>> matrix(n, std::vector<int>(m));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            matrix[i][j] = dist(gen);
        }
    }

    // Display the generated matrix
    std::cout << "Random " << n << "x" << n << " Matrix:\n";


    // Compute the MI matrix using multiple threads
    std::vector<std::vector<double>> mi_matrix = compute_mi_matrix_multithreaded(matrix, num_threads);

    // Display the MI matrix
    std::cout << "\nMutual Information Matrix:\n";


    return 0;
}
