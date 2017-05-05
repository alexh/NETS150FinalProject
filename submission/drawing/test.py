#imports the graph-tools library
from graph_tool.all import *

#opens your file in mode "read"
f = open("test2.txt","r")
#splits each line into a list of integers
lines = [x.split() for x in f.readlines()]
#closes the file
f.close()

#makes the graph
g = Graph()
#adds enough vertices (the "1 + " is for position 0)
# i = 0
# while i < 23:
# 	g.add_vertex()
# 	i += 1

g.add_vertex(23)


#makes a "property map" to weight the edges
property_map = g.new_edge_property("string")
v_prop = g.new_vertex_property("string")

names = ["Token_Druid", 
"Jade_Druid", 
"Midrange_Hunter", 
"Burn_Mage", 
"Exodia_Mage", 
"Freeze_Mage", 
"Secret_Mage", 
"Control_Paladin", 
"Ramp_Druid", 
"Elemental_Shaman", 
"Midrange_Paladin", 
"Murloc_Paladin", 
"Dragon_Priest", 
"Miracle_Priest", 
"Silence_Priest", 
"Crystal_Rogue", 
"Miracle_Rogue", 
"Aggro_Shaman", 
"Murloc_Shaman", 
"Hand_Warlock",
"Zoo_Warlock",
"Pirate_Warrior",
"Taunt_Warrior"]

vertex_size = g.new_vertex_property("int")
edge_size = g.new_edge_property("int")
for i in range(0, 23):
	v_prop[i] = names[i]
	vertex_size[i] = 2
# for each line
for line in lines:
    #make a new edge
    v1 = g.vertex(line[0])
    v2 = g.vertex(line[1])
    g.add_edge(v1, v2)
    #weight it
    property_map[g.edge(v1,v2)] = line[2]
    edge_size[g.edge(v1,v2)] = 1
pos = radial_tree_layout(g, g.vertex(0))
graph_draw(g, vertex_size = vertex_size, edge_pen_width = edge_size, vertex_text=v_prop, 
	edge_text=property_map, edge_font_size=18, vertex_font_size=18, output_size=(5000, 5000), output="graph.png")


