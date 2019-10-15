import sys
import re

def main():
    with open(sys.argv[1], 'r', encoding="utf-8") as archivo:
        lineas = archivo.readlines()
        numeroLineas = len(lineas)
        numeroPalabras = 0
        numeroSilabas = 0
        exprPalabras = r'[a-zA-Z]+'
        exprSilabas = r'[A-Z]'
        frases = []
        comentarios = []
        for linea in lineas:
            if len(linea) != 0 and re.search(exprPalabras, linea) is not None:
                linea = linea.strip()
                palabras = re.findall(exprPalabras, linea)
                numeroPalabras += len(palabras)
                for palabra in palabras:
                    numeroSilabas += len(re.findall('[A-Z]', palabra[1:])) + 1
                    print(palabra, len(re.findall('[A-Z]', palabra[1:])) + 1)
                frases.append(linea)


if __name__ == "__main__":
    main()