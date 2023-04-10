# a4-urbanism-derronli
- Derron Li [li1578@mcmaster.ca]

### Running The Project
_Please refer to the file "READMEA2_A3.md" for information on the generator, visualizer or island subprojects_

I will assume you are in the base project directory.  
The first step is to build a base input mesh from the generator subproject. For simplicity sake, an ideal input mesh for island generation can be built from the following command:
```
mvn clean install
mvn compile
mvn package
cd generator  
java -jar generator.jar input.mesh -pt 0 -ir -rl 40 -np 400
```

The next step is generating the actual island. For this urbanism project the only field you need to be concerned with is the "-city" parameter.  
This field is inputted with the island.jar and allows the user to control the number of cities to be generated on the island.  
A sample input is provided below.
```
cd island  
java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle -elevation volcano -soil dry -lake 5 -river 4 -aquifer 2 -city 15
```
As seen, the above input would generate 15 cities on the island  
_Please see the file "READMEA2_A3.md" for information regarding the other parameters_  
  
Finally, the island SVG is generated from the below command
```
cd visualizer
java -jar visualizer.jar -i ../island/island.mesh -o island.svg
```
To view the final product, open the visualizer subproject folder and open the "island.svg" file  
  
  
### Running Tests
Tests in the pathfinder subproject can be run by going to:  
pathfinder subproject folder >> src >> test >> java >> Main  
The Main file can then be directly run through your method of choice (ie. Intellij)  
Results will be outputted to the terminal

### Rationale
This project extends upon A2 and A3 where we built islands from meshes. The present business logic is to implement roads and cities on the island.
In order to accomplish this, a separate library was built for pathfinding. This library is entirely generic and supports building Graphs. Shortest paths are
found through Dijkstra's Algorithm. In order to use the pathfinding library on the island mesh, an adapter was created to "translate" the mesh to a suitable graph.

## Important Notes
- "Shortest path" in regards to the mesh is defined as the euclidian distance between nodes. This is accomplished by using the distance formula on a pair of vertices' (x,y) coordinates, and assigning the distance to the weight of an edge.
- Cities are assigned to polygon centroids, roads are built along neighbourhood relations
- Edges are assumed to be undirected in the graph, however the interface further supports other forms of edges to be extended
- The capital city in the star network is depicted by a lighter shade of red
