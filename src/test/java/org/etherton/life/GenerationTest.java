package org.etherton.life;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class GenerationTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GenerationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GenerationTest.class );
    }

    /**
     * Rigorous Test
     */
    public void testGeneration()
    {
    	//test calculating neighbors
    	
    	//no neighbors
    	boolean[][] board1 = {
    			{false, false, false},
    			{false, true, false},
    			{false, false, false}
    	};
    	Generation gen1 = new Generation(board1);
    	int neighbors = gen1.calcLivingNeighbors(1, 1);    	
        assertTrue( neighbors == 0 );
        
        //in the top left corner
        neighbors = gen1.calcLivingNeighbors(0, 0);    	
        assertTrue( neighbors == 1 );
        
        //in the bottom left corner
        neighbors = gen1.calcLivingNeighbors(0, 2);    	
        assertTrue( neighbors == 1 );        
        
        //in the top right corner
        neighbors = gen1.calcLivingNeighbors(2, 0);    	
        assertTrue( neighbors == 1 );
        
        //in the bottom right corner
        neighbors = gen1.calcLivingNeighbors(2, 2);    	
        assertTrue( neighbors == 1 );
        
        //all neighbors
    	boolean[][] board2 = {
    			{true, true, true},
    			{true, false, true},
    			{true, true, true}
    	};
    	Generation gen2 = new Generation(board2);
    	neighbors = gen2.calcLivingNeighbors(1, 1);    	
        assertTrue( neighbors == 8 );
        
        //in the top left corner
        neighbors = gen2.calcLivingNeighbors(0, 0);    	
        assertTrue( neighbors == 2 );
        
        //in the bottom left corner
        neighbors = gen2.calcLivingNeighbors(0, 2);    	
        assertTrue( neighbors == 2 );        
        
        //in the top right corner
        neighbors = gen2.calcLivingNeighbors(2, 0);    	
        assertTrue( neighbors == 2 );
        
        //in the bottom right corner
        neighbors = gen2.calcLivingNeighbors(2, 2);    	
        assertTrue( neighbors == 2 );
        
        boolean[][] firstGenBoard = {
        		{false, true, false, false, false},
        		{true, false, false, true, true},
        		{true, true, false, false, true},
        		{false, true, false, false, false},
        		{true, false, false, false, true}
        };
        
        gen1 = new Generation(firstGenBoard);
        gen2 = gen1.createNextGeneration();
        String gen2String = gen2.toString();
        String expectedResult = "0 0 0 0 0\n1 0 1 1 1\n1 1 1 1 1\n0 1 0 0 0\n0 0 0 0 0";
        assertTrue( gen2String.equals(expectedResult));
        
    }
}
