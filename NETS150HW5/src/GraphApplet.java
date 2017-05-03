import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import com.jgraph.layout.organic.JGraphOrganicLayout;
import com.jgraph.layout.organic.JGraphSelfOrganizingOrganicLayout;
import com.jgraph.layout.tree.JGraphRadialTreeLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import org.jgrapht.graph.DefaultEdge;

/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 *
 * @since Aug 3, 2003
 */
public class GraphApplet extends JApplet {
    private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    // 
    private JGraphModelAdapter m_jgAdapter;

    /**
     * @see java.applet.Applet#init().
     */
    public void init( Graph ourGraph ) {
        resize( DEFAULT_SIZE );
        mxGraph graph = new mxGraph();        
        Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
	        int y = 40;
	        HashMap<Vertex, Object> addedVertices = new HashMap<Vertex, Object>();
	        
	        for(Entry<Vertex, LinkedList<Vertex>> entry : ourGraph.adjList.entrySet())
	        {
	        	Vertex v = entry.getKey();
	        	LinkedList<Vertex> neighbors = entry.getValue();
	        	
	        	//Check if vertex exists already
	        	//if not, draw it
	        	if(!addedVertices.containsKey(v))
	        	{
	        		addedVertices.put(v, graph.insertVertex(parent, null, v.toString(), 20, y, 80, 30));
	        		y += 100;
	            	
	        	}
	        	
	        	//Add the neighbors
	        	for(Vertex neighbor : neighbors)
	        	{
	        		if(!addedVertices.containsKey(neighbor))
	        		{
		        		addedVertices.put(neighbor, graph.insertVertex(parent, null, v.toString(), 20, y, 80, 30));
	            		y += 100;
	        		}
	        		
	        	}
	        }
	        
	        //now go through edges
	        for(Edge edge : ourGraph.edges)
	        {
	    		//add the edge between the current node and the neighbor
	    		graph.insertEdge(parent, null, edge.weight, addedVertices.get(edge.src), addedVertices.get(edge.dest));
	        }
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		
		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent, BorderLayout.CENTER);
		new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
		new mxParallelEdgeLayout(graph).execute(graph.getDefaultParent());
		getContentPane().setSize(DEFAULT_SIZE);
    }


    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }


    
    
    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D        bounds    = GraphConstants.getBounds( attr );
        Rectangle2D newBounds = new Rectangle2D.Double(x,y,bounds.getWidth(),bounds.getHeight());

        GraphConstants.setBounds( attr,newBounds );

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null );
    }
}