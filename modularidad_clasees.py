import re

# Mide el promedio de numero de lineas que contienen todas las clases
class ModularidadClases:

    # Realiza el calculo de lineas totales entre clases existentes
    def obtener_modularidad(self, lineas):
        numero_clases = 0
        for linea in lineas:
            # Busca donde comienza una nueva clase
            es_clase = re.search('class', linea)
            if es_clase is not None:
                numero_clases += 1
            # Omite lineas en blanco
            if not linea.strip() == '':
                numero_lineas += 1
        return numero_lineas / numero_clases