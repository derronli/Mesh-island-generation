generator:
	cd generator
	java -jar generator.jar sample.mesh
	ls -lh sample.mesh

visualizer:
	cd visualizer
	java -jar visualizer.jar ../generator/sample.mesh sample.svg
	ls -lh sample.svg
