g:
	cd generator && java -jar generator.jar sample.mesh

v:
	cd visualizer && java -jar visualizer.jar ../generator/sample.mesh sample.svg

s:
	mvn clean install
	mvn compile
	mvn package

run: s g v

.PHONY: generator visualizer
