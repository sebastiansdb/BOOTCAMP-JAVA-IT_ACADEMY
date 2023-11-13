package S1.Collections.Teoria;

public class CollectionsInterfaces {

    /**
    The core collection interfaces encapsulate different types of collections, which are shown in the figure below.
    These interfaces allow collections to be manipulated independently of the details of their representation. Core
    collection interfaces are the foundation of the Java Collections Framework. As you can see in the following figure,
    the core collection interfaces form a hierarchy.

    ------------------------------------------------------------
    Collection Interface  (I)                (Interface Padre)
    |__  Set              (I)         |
        |__ SortedList    (I)         |
    |__  List             (I)         |      (Interfaces Hijas)
    |__  Queue            (I)         |
    |__  Dequeue          (I)         |
    ------------------------------------------------------------
    Map (I)
     |__    HashMap
     |__    HashMap

    ------------------------------------------------------------
    A Set is a special kind of Collection, a SortedSet is a special kind of Set, and so forth. Note also that the
    hierarchy consists of two distinct trees — a MAP is not a true Collection.


     ++++++++++++++++       ----------------------------------------------------------------        +++++++++++++++++
                                                     SET Interface

     Clases que implementan esta Interface:
                                        |__     HASHSET
                                        |__     LINKEDHASHET
                                        |__     TREESET

     HASHSET, which stores its elements in a hash table, is the best-performing implementation; however it makes
     no guarantees concerning the order of iteration. TREESET, which stores its elements in a red-black tree, orders its elements based on their values;
     it is substantially slower than HASHSET. LINKEDHASHSET, which is implemented as a hash table with a linked list running through it, orders its elements
     based on the order in which they were inserted into the set (insertion-order). LINKEDHASHSET spares its clients from the unspecified, generally chaotic
     ordering provided by HashSet at a cost that is only slightly higher.


     * HASHSET Class

        Esta clase no tiene métodos propios, todos los métodos que tiene son los que estan declarados en Set (I).

     Propiedades:
        1) NO se permiten DUPLICADOS.
        2) NO se preserva el orden de inserción, o sea, los elementos se insertan en orden aleatorio.
        3) Usa el concepto HASHCODE (No existe el concepto de INDICE) para insertar los elementos y para buscarlos dentro del HashSet.
           Por este motivo, la búsqueda dentro de un HashSet es MUY RAPIDA.
        4) Soporta la HETEROGENEIDAD (almacenar Objetos de distintos tipos).
        5) Soporta el Null. (Podemos almacenar el Null).
        6) Esxiste el "Load Factor" o "Filling Ratio", es un concepto muy importante en HashSet

     Instanciamiento

        - HashSet hs = new HashSet();

        Por defecto, con esta forma de declararlo, tendrá una capacidad para almacenar 16 elementos ("capacidad inicial")
     y un "Load Factor" de 0,75 (75%).

        Cuando se van guardando objetos y se utiliza un 75% (Load Factor) de la capacidad del HashSet, en el momento en que se agrega el siguiente objeto,
     se crea un nuevo HashSet (otra referencia en memoria) con capacidad para 17 elementos y se copia todo el contenido del anterior en este nuevo HashSet.

     Instancia indicando "Initial Size":
        - HashSet hs = new HashSet(100);                   // Initial size = 100

     Instancia indicando también el "Load Factor":
        - HashSet hs = new HashSet(100, (float)0.95);      // Debo hacer el casting en el parámetro "Load Factor" (No sé por qué)

     Si quiero RESTRINGIR a un solo tipo de Objeto para almacenar:

        - HashSet<Integer> hs = new HashSet();


     - Métodos de HashSet

        - add (Value or Object);                  // Como no existe el concepto de Index aquí, debemos pasarle el valor/objeto a agregar.
        - addAll (Collection);
        - remove(Value or Object);
        - removeAll (Collection);
        - contain (Object);
        - containAll (Collection);
        - isEmpty();                               // Devuelve boolean

        Los métodos SORT y SHUFFLE no están disponibles ( En el caso de List(I), sí estan ) porque no tenemos el concepto de Index. Tampoco
     tenemos el método GET() para acceder a un elemento puntual, o el método SET(), para modificar/actualizar un elemento puntual.

        Para acceder a los elementos del HashSet, debemos usar un ForEach o un Itrerator. ESTAS SON LAS UNICAS DOS MANERAS DE LEER LOS ELEMENTOS DEL MISMO.

        Si queremos ordenar los elements de un HashSet, debemos transformar éste en un HashMap o ArrayList y luego usar el método Sort.

     Operaciones entre HashSet's:

     -  Union
     set1.addAll(set2);

        Se agregan a set1 todos los elementos de set2 que no estan en set1. Lo que se hace es una union de ambos HashSet, pero como éstos no permiten
     duplicados, sólo se agregaran los elementos de set2 que no estén en set1.

     -  Interseccion
     set1.retainAll(set2);

        Quedarán en set1 solamente los elementos de set2 que estén también en set1 (interseccion propriamente dicha).

     -  Diferencia
     set1.removeAll(set2);

        Todos los elements de set2 que estén en set1, serán eliminados.

     - Subset
     set1.containsAll(set2);

        Devuelve true si todos los elementos de set2 está en set1, o sea, si set2 es un subset o subconjunto de set1.

     ++++++++++++++++       ----------------------------------------------------------------        +++++++++++++++++

                                                     Map INTERFACE

        -   Duplicate Keys are not allowed
        -   Duplicate Values are allowed

     * TreeMap CLASS

     -  TreeMap is sorted according to the natural ordering of its 'keys'.

     Ejemplos para ordenar HashMap por "claves":

     Ejemplo 1
     Map<String, String> map = new HashMap<>();
     Map<String, String> treeMap = new TreeMap<>(map);
     for (String str : treeMap.keySet()) {
     System.out.println(str);
     }

     Ejemplo 2
     List sortedKeys=new ArrayList(yourMap.keySet());
     Collections.sort(sortedKeys);
     // Do what you need with sortedKeys.

     Ejemplo 3:
     (If the map is passed to you and you cannot determine the type, then you can do the following)
     SortedSet<String> keys = new TreeSet<>(yourMap.keySet());
     for (String key : keys) {
     String value = yourMap.get(key);
     // do something
     }



     */




}
