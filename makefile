generator:
	cd generator
	java -jar generator.jar sample.mesh
	ls -lh sample.mesh
	cd ..

visualizer:
	cd visualizer
	java -jar visualizer.jar ../generator/sample.mesh sample.svg
	ls -lh sample.svg
	cd ..

.PHONY: generator

.PHONY2: visualizer
