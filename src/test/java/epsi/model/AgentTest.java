package epsi.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class AgentTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testGetNom() {
        System.out.println("test GetNom()");
        Agent agent = new Agent();
        agent.setNom("nomTest");
        String expectedResult = "nomTest";
        String result = agent.getNom();
        assertEquals(expectedResult, result);
    }

    public void testGetSetPrenom() {
        System.out.println("test GetPrenom()");
        Agent agent = new Agent();
        agent.setPrenom("prenomTest");
        String expectedResult = "prenomTest";
        String result = agent.getPrenom();
        assertEquals(expectedResult, result);
    }


    public void testGetSetProfession() {
        System.out.println("test GetProfession()");
        Agent agent = new Agent();
        agent.setProfession("professionTest");
        String expectedResult = "professionTest";
        String result = agent.getProfession();
        assertEquals(expectedResult, result);
    }

    public void testGetSetMdp() {
        System.out.println("test GetMdp()");
        Agent agent = new Agent();
        agent.setMdp("mdpTest");
        String expectedResult = "mdpTest";
        String result = agent.getMdp();
        assertEquals(expectedResult, result);
    }


    public void testGetSetAgentUniqueId() {
        System.out.println("test GetAgentUniqueId()");
        Agent agent = new Agent();
        agent.setAgentUniqueId("GetAgentUniqueIdTest");
        String expectedResult = "GetAgentUniqueIdTest";
        String result = agent.getAgentUniqueId();
        assertEquals(expectedResult, result);
    }


    public void testGetSetImageId() {
        System.out.println("test GetImageId()");
        Agent agent = new Agent();
        agent.setImageId("testGetImageIdTest");
        String expectedResult = "testGetImageIdTest";
        String result = agent.getImageId();
        assertEquals(expectedResult, result);
    }


    public void testGetSetMateriaux() {
        System.out.println("test GetMateriaux()");
        Agent agent = new Agent();
        List<String> mat = new ArrayList<>();
        mat.add("mat1");
        mat.add("mat2");
        agent.setMateriaux(mat);
        List<String> expectedResults = mat;
        List<String> results = agent.getMateriaux();
        assertEquals(expectedResults, results);
    }

}