g:
	cd generator && java -jar generator.jar sample.mesh

v:
	cd visualizer && java -jar visualizer.jar ../generator/sample.mesh sample.svg

.PHONY: generator visualizer
