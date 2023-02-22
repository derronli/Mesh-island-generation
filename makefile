g:
	cd generator && java -jar generator.jar sample.mesh

v:
	cd visualizer && java -jar visualizer.jar ../generator/sample.mesh sample.svg

vd:
	cd visualizer && java -jar visualizer.jar ../generator/sample.mesh sample.svg -X

s:
	mvn clean install
	mvn compile
	mvn package

run: s g v

rund: s g vd

.PHONY: generator visualizer
