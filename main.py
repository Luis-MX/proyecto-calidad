import sys
import re
from fog import IndiceFog

def main():
    with open(sys.argv[1], 'r', encoding="utf-8") as archivo:
        lineas = archivo.readlines()
        indice = IndiceFog(lineas)
        print('El indice de', sys.argv[1], 'es:', indice)


if __name__ == "__main__":
    main()