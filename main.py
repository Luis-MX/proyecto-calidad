import sys
import re
from fog import IndiceFog
from fog import IndiceFog
from DensidadComentarios import DensidadComentarios
from modularidad_clasees import ModularidadClases

def main():
    with open(sys.argv[1], 'r', encoding="utf-8") as archivo:
        lineas = archivo.readlines()        
        indice = IndiceFog(lineas)
        modularidad = ModularidadClases(lineas)
        densidad = DensidadComentarios.calcular_densidad(lineas)             
        print('El indice de', sys.argv[1], 'es:', str(indice.obtenerIndice()))
        print('La Densidad de Comentarios', sys.argv[1], "es", densidad)
        print('Las lineas de codigo por Clases', sys.argv[1], "es", modularidad.obtenerModularidad())

if __name__ == "__main__":
    main()