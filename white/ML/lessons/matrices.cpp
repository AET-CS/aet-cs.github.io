#include <iostream>
#include <vector>
#include <random>
#include <pybind11/pybind11.h>

// Matrix Multiply Naive Algorithm
// Patrick White 2024
// Based on a conversation with ChatGPT-4o

// name this file matrices.cpp and
// compile this file with
//  c++ -O3 -Wall -shared -std=c++11 -fPIC $(python3 -m pybind11 --includes) matrices.cpp -o multiplyMatrices$(python3-config --extension-suffix)

// then in python simply
// import pybind11
// import multiplyMatrices
// multiplyMatrices.multiplyBySize(10)

// make sure you have 'pip install pybind11' also

// if you just want to run it as an executable then
// c++ -O3 matrices.cpp -o matrices.o
// and run it as
// $ ./matrices.o 5
// where 5 means a 32x32 matrix (2^5)

namespace py = pybind11;

using namespace std;

// Function to multiply two matrices
vector<vector<int>> multiplyMatrices(const vector<vector<int>>& mat1, const vector<vector<int>>& mat2) {
    int rows1 = mat1.size();
    int cols1 = mat1[0].size();
    int cols2 = mat2[0].size();

    // Result matrix with size rows1 x cols2
    vector<vector<int>> result(rows1, vector<int>(cols2, 0));

    // Multiply mat1 by mat2
    for (int i = 0; i < rows1; ++i) {
        for (int j = 0; j < cols2; ++j) {
            for (int k = 0; k < cols1; ++k) {
                result[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
    }

    return result;
}

// Function to generate a random n x n matrix of integers
vector<vector<int>> generateRandomIntMatrix(int n, int minVal, int maxVal) {
    vector<vector<int>> matrix(n, vector<int>(n));

    // Seed for random number generation
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(minVal, maxVal); // Random integers between minVal and maxVal

    // Fill the matrix with random integers
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            matrix[i][j] = dis(gen);
        }
    }

    return matrix;
}


// Function to print a matrix
void printMatrix(const vector<vector<int>>& matrix) {
    for (const auto& row : matrix) {
        for (int elem : row) {
            cout << elem << " ";
        }
        cout << endl;
    }
}

// quick multiply test routine to use in python
// we ignore the result

void multiplyBySize(int n) {
		vector<vector<int>> mat1 = generateRandomIntMatrix(n,-20,20);
		vector<vector<int>> mat2 = generateRandomIntMatrix(n,-20,20);

		// Check if matrix multiplication is possible
		if (mat1[0].size() != mat2.size()) {
				cout << "Matrix multiplication not possible!" << endl;
		}

		// Multiply the matrices
		vector<vector<int>> result = multiplyMatrices(mat1, mat2);
}

// this is the one thing you need to add
// to expose a C++ method to the python library
// when using pybind
// notice my version is simplified because
// I ignore the return value!

PYBIND11_MODULE(multiplyMatrices, m){
	m.def("multiplyBySize", &multiplyBySize, "A function to multiply two matrices of size n");
}

int main(int argc,char** argv) {
		// read matrix size as int from stdin
		// no error checking!
		int n = atoi(argv[1]);
    vector<vector<int>> mat1 = generateRandomIntMatrix(n,-20,20);
    vector<vector<int>> mat2 = generateRandomIntMatrix(n,-20,20);

    // Check if matrix multiplication is possible
    if (mat1[0].size() != mat2.size()) {
        cout << "Matrix multiplication not possible!" << endl;
        return 1;
    }

    // Multiply the matrices
    vector<vector<int>> result = multiplyMatrices(mat1, mat2);

    // Print the result
    cout << "Result matrix:" << endl;
    printMatrix(result);

    return 0;
}
