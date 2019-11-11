# jumble

It scrambles the given string and checks if it can be rearranged to match the given word. 

## Usage

Clone the repository and run the project:

    $ clj -m adgoji.scramble.main

This will start the server on port 3000. Connect with your browser on http://localhost:3000/. This will serve the application. (The compiled cljs (not minified) is included in the repo for ease of running the application.)

To run the clj tests - 

    $ clj -A:test:runner

To run the cljs tests - 

    $ shadow-cljs compile test
    
The benchmarks are under dev/benchmarks.clj and generative tests in models_test.clj
