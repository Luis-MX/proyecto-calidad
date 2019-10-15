import re

class ModularidadClases:

    def __init__(self, lineas):
        self.lineas = lineas

    def obtenerModularidad(self):
        numeroLineas = len(self.lineas)
        numeroClases = 0
        for linea in self.lineas:
            esComentario = re.search('class', linea)
            if esComentario is not None:
                numeroClases += 1
        return numeroLineas / numeroClases