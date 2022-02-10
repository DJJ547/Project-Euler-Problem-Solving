# Problem #527
# A secret integer t is selected at random within the range 1 ≤ t ≤ n.
#
# The goal is to guess the value of t by making repeated guesses, via integer g.
# After a guess is made, there are three possible outcomes, in which it will be
# revealed that either g < t, g = t, or g > t. Then the process can repeat as necessary.
#
# Normally, the number of guesses required on average can be minimized with a binary
# search: Given a lower bound L and upper bound H (initialized to L = 1 and H = n),
# let g = ⌊(L+H)/2⌋ where ⌊⋅⌋ is the integer floor function. If g = t, the process ends.
# Otherwise, if g < t, set L = g+1, but if g > t instead, set H = g−1.
# After setting the new bounds, the search process repeats, and ultimately ends once t is found.
# Even if t can be deduced without searching, assume that a search will be required anyway
# to confirm the value.
#
# Your friend Bob believes that the standard binary search is not that much better
# than his randomized variant: Instead of setting g = ⌊(L+H)/2⌋, simply let g be a random
# integer between L and H, inclusive. The rest of the algorithm is the same as the standard
# binary search. This new search routine will be referred to as a random binary search.
#
# Given that 1 ≤ t ≤ n for random t, let B(n) be the expected number of guesses needed
# to find t using the standard binary search, and let R(n) be the expected number of guesses
# needed to find t using the random binary search. For example, B(6) = 2.33333333
# and R(6) = 2.71666667 when rounded to 8 decimal places.
#
# Find R(1010) − B(1010) rounded to 8 decimal places.
import random


def binary(high, low, num):
    search_num = (low + high) // 2
    print("first searched num: {}".format(search_num))
    num_of_guess = 1
    while not search_num == num:
        if search_num < num:
            low = search_num + 1
            print("new range: {} - {}".format(low, high))
        elif search_num > num:
            high = search_num - 1
            print("new range: {} - {}".format(low, high))
        search_num = (low + high) // 2
        print("new searched num: {}".format(search_num))
        num_of_guess += 1

    print("number of guesses from normal binary: {}".format(num_of_guess))
    return num_of_guess


def randomized_binary(high, low, num):
    random_num = random.randrange(low, high)
    print("first randomized num: {}".format(random_num))
    num_of_guess = 1
    while not random_num == num:
        if random_num < num:
            low = random_num + 1
            print("new range: {} - {}".format(low, high))
        elif random_num > num:
            high = random_num - 1
            print("new range: {} - {}".format(low, high))
        if high == low:
            break
        random_num = random.randrange(low, high)
        print("new randomized num: {}".format(random_num))
        num_of_guess += 1

    print("number of guesses from randomized binary: {}".format(num_of_guess))
    return num_of_guess


def main():
    n = 10 ** 10
    upper_bound = n
    lower_bound = 1
    generated_random_num = random.randrange(1, n)
    print("set rendom num: {}".format(generated_random_num))
    binary_num_guess = binary(upper_bound, lower_bound, generated_random_num)
    print('\n\n')
    randomized_num_guess = randomized_binary(upper_bound, lower_bound, generated_random_num)
    print(randomized_num_guess - binary_num_guess)


if __name__ == '__main__':
    main()
