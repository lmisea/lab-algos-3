/*
 * Proyecto 2 del Laboratorio de Algoritmos y Estructuras de Datos III
 * Autores: Juan Cuevas (19-10056) y Luis Isea (19-10175).
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyListGraphProj2<T> implements GraphProj2<T> {
	/**
	 * Representación de un grafo no dirigido mediante listas de adyacencia.
	 * Creamos el HashMap que contiene una lista de adyacencia para cada vértice.
	 * El HashMap contiene los vertices adyacentes a cada vértice.
	 * Sea el grafo G = (V, E) con |V| = n y |E| = m, el HashMap tiene n
	 * entrada y la suma de las longitudes de las listas de adyacencia es 2m.
	 */
	HashMap<T, List<T>> adjacencyList = new HashMap<T, List<T>>();

	/**
	 * Recibe un vértice y lo agrega al grafo. Retorna true si el vértice es
	 * agregado con éxito.
	 * Retorna false en caso contrario.
	 * Complejidad: O(1).
	 */
	public boolean add(T vertex) {
		// Si el vértice ya existe en el grafo, no se agrega y se retorna false.
		if (contains(vertex)) {
			return false;
		}
		// Si el vértice es null, no se agrega y se retorna false.
		if (vertex == null) {
			return false;
		}
		// Se mapea el vértice a una lista vacía en el HashMap.
		adjacencyList.put(vertex, new LinkedList<T>());
		return true;
	}

	/**
	 * Recibe dos vértices 'from' y 'to' y agrega al grafo la arista que une a
	 * 'from' y 'to'.
	 * Retorna true si el lado es agregado con éxito.
	 * Retorna false en caso contrario.
	 * Complejidad: O(n). Siendo n la cantidad de vértices.
	 * Ya que se debe verificar que la arista no exista en el grafo.
	 * Y para ello se debe recorrer la lista de adyacencia de 'from' buscando a
	 * 'to'.
	 */
	public boolean connect(T from, T to) {
		// Si alguno de los vértices no existe en el grafo, no se agrega el lado y se
		// retorna false.
		if (!contains(from) || !contains(to)) {
			return false;
		}
		// Si el lado ya existe en el grafo, no se agrega el lado y se retorna false.
		if (areConnected(from, to)) {
			return false;
		}
		// Se añade el vértice 'to' a la lista de adyacencia de 'from'.
		adjacencyList.get(from).add(to);
		// Se añade el vértice 'from' a la lista de adyacencia de 'to' si son distintos.
		if (from != to) {
			adjacencyList.get(to).add(from);
		}
		return true;
	}

	/**
	 * Recibe dos vértices 'from' y 'to' y elimina del grafo la arista que une a
	 * 'from' y 'to'.
	 * Retorna true si el lado es eliminado con éxito.
	 * Retorna false en caso contrario.
	 * Si el lado no existe en el grafo, no se elimina el lado y se retorna false.
	 * Complejidad: O(n). Siendo n la cantidad de vértices.
	 */
	public boolean disconnect(T from, T to) {
		// Si alguno de los vértices no existe en el grafo, no se elimina el lado y se
		// retorna false.
		if (!contains(from) || !contains(to)) {
			return false;
		}
		// Si el lado no existe en el grafo, no se elimina el lado y se retorna false.
		if (!adjacencyList.get(from).contains(to)) {
			return false;
		}
		// Se elimina el vértice 'to' de la lista de adyacencia de 'from'.
		adjacencyList.get(from).remove(to);
		// Se elimina el vértice 'from' de la lista de adyacencia de 'to'.
		adjacencyList.get(to).remove(from);
		return true;
	}

	/**
	 * Recibe un vértice y retorna true si el vértice pertenece a V.
	 * Retorna false en caso contrario.
	 * Complejidad: O(1).
	 */
	public boolean contains(T vertex) {
		return adjacencyList.containsKey(vertex);
	}

	/**
	 * Recibe dos vértices 'from' y 'to' y retorna true si existe una arista entre
	 * ellos. Retorna false en caso contrario.
	 * Complejidad: O(n). Siendo n la cantidad de vértices.
	 * Ya que se recorre la lista de adyacencia de 'from' y se busca 'to'.
	 */
	public boolean areConnected(T from, T to) {
		// Si alguno de los vértices no existe en el grafo, no están conectados y se
		// retorna false.
		if (!contains(from) || !contains(to)) {
			return false;
		}
		// Si el vértice 'to' está en la lista de adyacencia de 'from', están conectados
		// y se retorna true.
		if (adjacencyList.get(from).contains(to)) {
			return true;
		}
		return false;
	}

	/**
	 * Recibe un vértice v y retorna la lista de vértices adyacentes a v. Es decir,
	 * retorna la lista de todos los u ∈ V tales que {v, u} ∈ E.
	 * Si ocurre algún error, retorna la referencia null.
	 * Complejidad: O(n), siendo n la cantidad de vertices.
	 * Esto ya que para copiar la lista, se debe recorrer cada elemento de
	 * la lista una vez.
	 */
	public List<T> getVerticesConnectedTo(T vertex) {
		// Si el vértice no existe en el grafo, se retorna null.
		if (!contains(vertex)) {
			return null;
		}
		// Se crea una copia de la lista de adyacencia del vértice y se retorna.
		List<T> result = new LinkedList<T>(adjacencyList.get(vertex));
		return result;
	}

	/**
	 * Retorna la lista de todos vértices del grafo.
	 * Es decir, todos los elementos de V.
	 * Complejidad: O(1).
	 */
	public List<T> getAllVertices() {
		return adjacencyList.keySet().stream().toList();
	}

	/**
	 * Recibe un vértice y lo elimina del grafo. Retorna true si el vértice es
	 * eliminado con éxito. Retorna false en caso contrario.
	 * Complejidad: O(n*m). Siendo n la cantidad de vértices y m la cantidad
	 * promedio de lados por vértice.
	 */
	public boolean remove(T vertex) {
		// Si el vértice no existe en el grafo, no se elimina y se retorna false.
		if (!contains(vertex)) {
			return false;
		}
		for (T edge : adjacencyList.get(vertex)) {
			// Se elimina el vértice de la lista de adyacencia de todos sus vértices
			// adyacentes.
			adjacencyList.get(edge).remove(vertex);
		}
		// Se elimina el vértice del HashMap.
		adjacencyList.remove(vertex);
		return true;
	}

	/**
	 * Retorna la cantidad de vértices que contiene el grafo. Es decir, la
	 * cardinalidad del conjunto de vértices |V|.
	 * Complejidad: O(1).
	 */
	public int size() {
		return adjacencyList.size();
	}

	/**
	 * Retorna una representación en String del grafo.
	 * Complejidad: O(n). Siendo n la cantidad de vértices.
	 */
	@Override
	public String toString() {
		String result = "";
		for (T vertex : adjacencyList.keySet()) {
			result += "[" + vertex + "] -> " + adjacencyList.get(vertex) + "\n";
		}
		return result;
	}
}
