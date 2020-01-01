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

### Rationale

A concise solution to the problem would be to just compare the frequencies of the string and the word. However below is a more performant solution -

a) In the first step `set` of both the strings is compared. This will weed out cases where the char set of the word is not in the string. Clojure implements the `set` function natively in java, while `frequencies` is implemented as a `reduce` operation, thus making the `set` function faster than the `frequencies` funciton. In benchmarks, the `set` operation is 62% faster compared to the `frequencies` operation on a 100 char string.

b) In the second step the `frequencies` function is applied only on the `word`. The assumption is that in practice the length of the `word` will be less than the `string`, thus it would be more performant to just apply the `frequencies` on the `word` and then loop over the `string` for multiple occurences of any chars. The looping is also done with `every` to short circuit the looping.

So though the above solution and the `only frequencies` solution both have the same worst case time complexity of O(n2), the former will be a faster algorithm in practice -

a) There will be atleast 50% non matching cases, and these will get weeded in the first step. (In these cases the time complexity of the above solution will be O(n), this is benchmarked under `user/benchmark.clj`)
b) The `string` to scramble will usually be bigger than the `word` string. (In these cases the costlier `frequencies` fn will be applied on the shorter string `word`, thus giving better performance.)
