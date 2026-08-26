from fractions import Fraction
from functools import lru_cache

numbers = (2, 3, 4, 5)
MAX_VALUE = 100_000
MAX_EXPONENT = 8


@lru_cache(None)
def expressions(mask):
    """Return {value: (infix_expression, rpn_expression)} for the numbers in mask."""
    if mask & (mask - 1) == 0:
        index = mask.bit_length() - 1
        number = str(numbers[index])
        return {Fraction(numbers[index]): (number, number)}

    results = {}
    split = (mask - 1) & mask

    while split:
        other = mask ^ split

        # Avoid repeating the same partition in reverse.
        if other and split < other:
            for left, (left_text, left_rpn) in expressions(split).items():
                for right, (right_text, right_rpn) in expressions(other).items():
                    candidates = [
                        (left + right, f"({left_text} + {right_text})", f"{left_rpn} {right_rpn} +"),
                        (left - right, f"({left_text} - {right_text})", f"{left_rpn} {right_rpn} -"),
                        (right - left, f"({right_text} - {left_text})", f"{right_rpn} {left_rpn} -"),
                        (left * right, f"({left_text} * {right_text})", f"{left_rpn} {right_rpn} *"),
                    ]

                    if right:
                        candidates.append(
                            (left / right, f"({left_text} / {right_text})", f"{left_rpn} {right_rpn} /")
                        )
                    if left:
                        candidates.append(
                            (right / left, f"({right_text} / {left_text})", f"{right_rpn} {left_rpn} /")
                        )

                    powers = [
                        (left, right, left_text, right_text, left_rpn, right_rpn),
                        (right, left, right_text, left_text, right_rpn, left_rpn),
                    ]

                    for base, exponent, base_text, exponent_text, base_rpn, exponent_rpn in powers:
                        if (
                            exponent.denominator == 1
                            and abs(exponent.numerator) <= MAX_EXPONENT
                            and not (base == 0 and exponent <= 0)
                        ):
                            value = base ** exponent.numerator
                            candidates.append(
                                (value, f"({base_text} ^ {exponent_text})", f"{base_rpn} {exponent_rpn} ^")
                            )

                    for value, text, rpn in candidates:
                        if abs(value) <= MAX_VALUE:
                            results.setdefault(value, (text, rpn))

        split = (split - 1) & mask

    return results


all_numbers = (1 << len(numbers)) - 1
reachable = expressions(all_numbers)

for target in range(1, 100):
    if Fraction(target) not in reachable:
        print("Smallest missing:", target)
        break

for target in range(1, 50):
    expression = reachable.get(Fraction(target))
    if expression is None:
        print(target, "= impossible")
    else:
        print(target, "=", expression[1])