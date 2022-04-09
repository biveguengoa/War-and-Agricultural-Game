SRC = \
	src/game/*.java \
	src/game/boardTypes/*.java \
	src/game/characters/*.java \
	src/game/exception/*.java \
	src/game/lands/*.java \
	src/game/lands/FarmersLand/*.java \
	src/game/lands/WarLand/*.java \
	src/game/util/io/*.java \
	src/game/gameTypes/*.java \
	src/game/TypePlayers/*.java


all: cls doc guerre.jar agricole.jar

cls:
	javac $(SRC) -d classes

clean: 
	rm -fr docs
	rm -fr classes
	rm -f jar/*.jar

doc: 
	javadoc $(SRC) -d ./docs

guerre.jar: classes/game
	cd classes; jar cvfm ../jar/guerre.jar ../jar/manifest-warGame game

agricole.jar: classes/game
	cd classes; jar cvfm ../jar/agricole.jar ../jar/manifest-farmerGame game
