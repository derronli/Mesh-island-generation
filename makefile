generator:
	cd generator && java -jar generator.jar sample.mesh

visualizer:
	cd visualizer && java -jar visualizer.jar ../generator/sample.mesh sample.svg

.PHONY: generator visualizer
