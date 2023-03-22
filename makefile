g:
	cd generator && java -jar generator.jar sample.mesh

gi:
	cd generator && java -jar generator.jar sample.mesh -ir

gt:
	cd generator && java -jar generator.jar sample.mesh -pa 150 -sa 170 -va 200 -pt 3 -st 2 -vt 2

git:
	cd generator && java -jar generator.jar sample.mesh -ir -pa 150 -sa 170 -va 200 -pt 3 -st 2 -vt 2 -np 100 -rl 20

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

# run grid with command line args
rungt: s gt v

# run irregular
runi: s gi v

# run irregular with command line args
runit: s git v

# run grid with command line args
rungtd: s gt vd

# run irregular
runid: s gi vd

# run irregular with command line args
runitd: s git vd

.PHONY: generator visualizer

genis:
	cd generator && java -jar generator.jar input.mesh -pt 0 -ir -rl 40 -np 400

genlagoon:
	cd island && java -jar island.jar -o lagoon.mesh -i ../generator/input.mesh -mode lagoon

vislagoon:
	cd visualizer && java -jar visualizer.jar ../island/lagoon.mesh lagoon.svg

runlag: s genlagoon vislagoon

visisland:
	cd visualizer && java -jar visualizer.jar ../island/island.mesh island.svg

gencir:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode circle

runcir: s gencir visisland

genhex:
	cd island && java -jar island.jar -o island.mesh -i ../generator/input.mesh -mode hexagon

runhex: s genhex visisland
