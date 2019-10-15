
""" Se calcula la densidad de comentarios de nuestros programas PSP"""
import sys #librería de sistema para leer argumentos de un script
from io import open #módulo de io para leer ficheros
import re #módulo de expresiones regulares

class DensidadComentarios:
            
    def calcular_densidad(lineas):

        total_loc = 0 #contamos las lineas totales del programa
        lineas_comentadas = 0 #lineas comentadas    
            
       #iteramos la lista de lineas        
        for i in range(len(lineas)):
            #quitamos las lineas en blanco con strip()
            if(not lineas[i].strip() == ''): 
                # print(lineas[i]) 
                total_loc += 1                             
                
                #contamos este tipo de comentarios (multiline)              
                if(lineas[i].strip().startswith("/*") or lineas[i].strip().startswith("*") or lineas[i].strip().startswith("*/")):
                   lineas_comentadas += 1
                   
                #contamos el comentario que va al final de una LOC                                                                  
                frase = lineas[i]   
                patron = '//'             
                encontrado = re.search(patron, frase)
                if(encontrado is not None):
                    lineas_comentadas +=1              
                                                                                                        
        densidad_comentarios = lineas_comentadas / total_loc
        # print(densidad_comentarios)
        return densidad_comentarios