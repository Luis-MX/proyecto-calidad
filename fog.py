import re

class IndiceFog:

    # Ceunta lineas no vacias y que no sean comentario y calcula el indice de fog con ellas
    def obtener_indice(self, lineas):
        numero_palabras = 0
        numero_palabras_complejas = 0
        numero_palabras_no_complejas = 0
        expr_palabras = r'[a-zA-Z]+' # Busca palabras con mas de una minuscula y mayuscula
        expr_silabas = r'[A-Z]' # Busca al menos una palabra en mayuscula
        frases = []
        for linea in lineas:
            # Comprueba que la linea contenga codigo valido
            if len(linea) != 0 and re.search(expr_palabras, linea) is not None:
                linea = linea.strip()
                palabras = re.findall(expr_palabras, linea)
                numero_palabras += len(palabras)
                # Analiza palabra por palabra si son complejas o simples
                for palabra in palabras:
                    numero_silabas = len(re.findall(expr_silabas, palabra[1:])) + 1
                    if numero_silabas < 3:
                        numero_palabras_no_complejas += 1
                    else:
                        numero_palabras_complejas += 1
                frases.append(linea)
        # Devuelve el resultado usando la formula del indice de fog
        return 0.4 * (numero_palabras / len(frases)) + 100 * (numero_palabras_complejas / numero_palabras)
    
    