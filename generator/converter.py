import sys

if __name__ == "__main__":
    if len(sys.argv) > 1:
        filepath = sys.argv[1]
    else:
        filepath = "file.dt"
    with open(filepath) as f:
        lines = f.readlines()
        print(lines)
