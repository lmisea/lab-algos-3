/*
 * Proyecto 1 del Laboratorio de Algoritmos y Estructuras de Datos III
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AdjacencyListGraph<T> implements Graph<T> {
	/*
	 * Creamos un HashMap que contiene una lista de adyacencia para cada vértice.
	 * Cada lista de adyacencia contiene los vértices adyacentes al vértice.
	 */
	HashMap<T, List<T>> adjacencyList = new HashMap<T, List<T>>();

    /*
     * Recibe un vértice y lo agrega al grafo. Retorna true si el vértice es
     * agregado con éxito.
     * Retorna false en caso contrario.
     * Si el vértice ya existe en el grafo, no se agrega y se retorna false.
     * Si el vértice es null, se retorna false.
     * Si el vértice es agregado con éxito, se crea una lista de adyacencia vacía para el vértice.
     * Complejidad: O(1)
     */
    public boolean add(T vertex) {
        if(contains(vertex)){
            return false;
        }
        if(vertex == null){
            return false;
        }
        adjacencyList.put(vertex, new ArrayList<T>());
        return true;
    }

	/*
	 * Recibe dos vértices 'from' y 'to' y agrega al grafo un arco saliente de
	 * 'from' y entrante a 'to'.
	 * Retorna true si el arco es agregado con éxito.
	 * Retorna false en caso contrario.
	 * Si alguno de los vértices no existe en el grafo, no se agrega el arco y
	 * se retorna false.
	 */
	public boolean connect(T from, T to) {
		if (!contains(from) || !contains(to)) {
			return false;
		}
		if (adjacencyList.get(from).contains(to)) {
			return false;
		}
		adjacencyList.get(from).add(to);
		return true;
	}

    /*
     * Recibe dos vértices 'from' y 'to' y elimina del grafo el arco saliente de
     * 'from' y entrante a 'to'.
     * Retorna true si el arco es eliminado con éxito.
     * Retorna false en caso contrario.
     * Si alguno de los vértices no existe en el grafo, no se elimina el arco y se retorna false.
     * Si el arco no existe en el grafo, no se elimina el arco y se retorna false.
     * Complejidad: O(1)
     */
    public boolean disconnect(T from, T to) {
        if(!contains(from) || !contains(to)){
            return false;
        }
        if(!adjacencyList.get(from).contains(to)){
            return false;
        }
        adjacencyList.get(from).remove(to);
        return true;
    }

    /*
     * Recibe un vértice y retorna true si el vértice pertenece a V.
     * Retorna false en caso contrario.
     * Complejidad: O(1)
     */
    public boolean contains(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    /*
     * Recibe un vértice v y retorna la lista de predecedores de v. Es decir,
     * retorna la lista de todos los u ∈ V tales que (u, v) ∈ E.
     * Si ocurre algún error, retorna la referencia null
     * Complejidad: 
     */
    public List<T> getInwardEdges(T to) {
        return null;
    }

    /*
     * Recibe un vértice v y retorna la lista de sucesores de v. Es decir, retorna
     * la lista de todos los u ∈ V tales que (v, u) ∈ E.
     * Si ocurre algún error, retorna la referencia null.
     * Complejidad: O(1)
     */
    public List<T> getOutwardEdges(T from) {
        if(!contains(from)){
            return null;
        }
        return adjacencyList.get(from).stream().toList();
    }

    /*
     * Recibe un vértice v y retorna la lista de vértices adyacentes a v. Es decir,
     * retorna la lista de todos los u ∈ V tales que (v, u) ∈ E o (u, v) ∈ E.
     * Si ocurre algún error, retorna la referencia null.
     * Complejidad:
     */
    public List<T> getVerticesConnectedTo(T vertex) {
        return null;
    }

    /*
     * Retorna la lista de todos vértices del grafo.
     * Es decir, todos los elementos de V.
     * Complejidad: O(1)
     */
    public List<T> getAllVertices() {
        return adjacencyList.keySet().stream().toList();
    }

    /*
     * Recibe un vértice y lo elimina del grafo. Retorna true si el vértice es
     * eliminado con éxito. Retorna false en caso contrario.
     * Si el vértice no existe en el grafo, no se elimina y se retorna false.
     * Complejidad: O(n)
     */
    public boolean remove(T vertex) {
        if(!contains(vertex)){
            return false;
        }
        adjacencyList.remove(vertex);
        for (T v : adjacencyList.keySet()) {
            adjacencyList.get(v).remove(vertex);
        }
        return true;
    }

    /*
     * Retorna la cantidad de vértices que contiene el grafo. Es decir, la
     * cardinalidad del conjunto de vértices |V|.
     * Complejidad: O(1)
     */
    public int size() {
        return adjacencyList.size();
    }

    /*
     * Recibe una colección V′ de vértices y retorna otra instancia de grafo
     * donde el conjunto de vértices contiene solo aquellos vértices presentes en V’
     * y solo aquellos arcos asociados a esos vértices. Es decir,
     * retorna G′ = (V′, E′)
     * donde E′ = {(u, v) ∈ E ∣ u ∈ V′ ∧ v ∈ V′}.
     * Si ocurre algún error, retorna la referencia null.
     * Complejidad: 
     */
    public Graph<T> subgraph(Collection<T> vertices) {
        return null;
    }
}
