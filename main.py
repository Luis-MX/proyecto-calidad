import sys
import re
from fog import IndiceFog
from densidad_comentarios import DensidadComentarios
from modularidad_clasees import ModularidadClases


class Programa:

    def __init__(self):
        with open(sys.argv[1], 'r', encoding="utf-8") as archivo:
            self.lineas = archivo.readlines()


    def main(self):
        indice = IndiceFog()
        modularidad = ModularidadClases()
        densidad = DensidadComentarios()             
        print('El indice de', sys.argv[1], 'es:', str(indice.obtener_indice(self.lineas)))
        print('La Densidad de Comentarios', sys.argv[1], "es", densidad.calcular_densidad(self.lineas))
        print('Las lineas de codigo por Clases', sys.argv[1], "es", modularidad.obtener_modularidad(self.lineas))

if __name__ == "__main__":
    programa = Programa()
    programa.main()