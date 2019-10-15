import re

class IndiceFog:

    def __init__(self, lineas):
        self.lineas = lineas

    def obtenerIndice(self):
        numeroLineas = len(self.lineas)
        numeroPalabras = 0
        numeroPalabrasComplejas = 0
        numeroPalabrasNoComplejas = 0
        exprPalabras = r'[a-zA-Z]+'
        exprSilabas = r'[A-Z]'
        frases = []
        for linea in self.lineas:
            if len(linea) != 0 and re.search(exprPalabras, linea) is not None:
                linea = linea.strip()
                palabras = re.findall(exprPalabras, linea)
                numeroPalabras += len(palabras)
                for palabra in palabras:
                    numeroSilabas = len(re.findall(exprSilabas, palabra[1:])) + 1
                    if numeroSilabas < 3:
                        numeroPalabrasNoComplejas += 1
                    else:
                        numeroPalabrasComplejas += 1
                frases.append(linea)
        return 0.4 * (numeroPalabras / len(frases)) + 100 * (numeroPalabrasComplejas / numeroPalabras)