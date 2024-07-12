# Distribución automática en múltiples máquinas del proceso de aprendizaje para redes neuronales

Repositorio de mi Trabajo de Fin de Grado en la Universidad de Granada.

## Contenidos

 - **[Scala Spark Distributed Learning](scala-spark-distributed-learning)**: Contiene el código del proyecto. El archivo `main.scala` contiene el código necesario para ejecutar las pruebas del capítulo 8 de la memoria.

 - **[Memoria](memoria)**: Contiene el proyecto LaTeX con la memoria del trabajo.

## Resumen

En las últimas décadas, los avances en el campo del aprendizaje automático o ML
(machine learning en inglés) han adopción masiva de sus métodos y técnicas en el
sector privado. Estas técnicas necesitan muchas veces realizar cálculos repetitivos
sobre grandes volúmenes de datos para obtener resultados útiles. La utilización de
estas técnicas supone la ejecución de operaciones que necesitan ser completadas en
un tiempo “razonable”. Conforme los modelos se vuelven más complejos, el tiempo
necesario para realizar los cálculos aumenta considerablemente. Es por ello
que la ejecución paralela y distribuida de los mencionados cálculos se ha convertido
en un proceso clave para que el proceso de entrenamiento sea factible y se pueda
llevar a cabo eficientemente.

El objetivo de este proyecto es ofrecer una herramienta que permita automatizar
la distribución y paralelización, en unidades de procesamiento gráficas, el proceso
de aprendizaje de redes neuronales, consiguiendo de esta manera suavizar la curva
de aprendizaje de técnicas de paralelización para personas sin los conocimientos
técnicos necesarios para obtener rendimiento de la programación paralela.

Los objetivos incialmente propuestos del trabajo son los siguientes: En primer lugar,
la transformación automática de secciones de código secuencial de entrenamiento de
redes neuronales mediante llamadas a librerías específicas también implementadas
en el trabajo que hagan posible la paralelización con GPUs. En segundo lugar, la
construcción de un intérprete a código de funciones seleccionadas que incluyen
código paralelizado para su ejecución en GPUs. En tercer lugar, aplicar conocimientos
de la teoría matemática fundamental de compiladores y autómatas para automatizar
el proceso de paralelización anterior. Finalmente, conseguir suavizar la curva de
aprendizaje de técnicas de paralelización para personas sin un conocimiento a bajo
nivel del hardware de los procesadores GPU.
