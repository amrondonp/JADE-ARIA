package demo.ContractNet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import examples.protocols.DutchAuctionInitiatorAgent;
import examples.protocols.DutchAuctionResponderAgent;
import examples.protocols.MyContractNetAgentInitiatior;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class ContractNetDemo {
	
	static Map<Integer, ArrayList<String>> loadFile() {
		Map<Integer, ArrayList<String>> data = new HashMap<>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\anrondon\\Desktop\\Coding\\JADE-ARIA\\Hashtable.csv"));
			String str;
			while((str = bf.readLine()) != null) {
				String [] separated = str.split(",");
				ArrayList<String> register = new ArrayList<>();
				
				for(int i = 1; i < separated.length; i++) {
					register.add(separated[i]);
				}
				
				data.put(Integer.parseInt(separated[0]), register);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	public static void main(String[] args) {
		Map<Integer, ArrayList<String>> data = loadFile();
	
		Runtime jade = Runtime.instance();
		Properties mainProps = new ExtendedProperties();
		mainProps.setProperty(Profile.MAIN, "true");
		mainProps.setProperty(Profile.GUI, "true");
		mainProps.setProperty(Profile.MAIN_HOST, "127.0.0.1");
		
		jade.createMainContainer(new ProfileImpl(mainProps));
		ContainerController container = jade.createAgentContainer(new ProfileImpl());
		
		try {
			ArrayList<AgentController> ctrls = new ArrayList<AgentController>();		
			
			for(Integer id: data.keySet()) {
				ArrayList<String> collumnsWithNoId = data.get(id);
				AgentController myController = container.createNewAgent(
						"Grupo " + id,
						MyContractNetAgentInitiatior.class.getCanonicalName(),
						new Object[] {collumnsWithNoId}
				);
				
				ctrls.add(myController);
			}
			
			
			//Thread.sleep(3000);
			for(AgentController ctrl : ctrls)
				ctrl.start();
		} catch(StaleProxyException e)
		{
			e.printStackTrace(); }
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try
//		{
//			AgentController myController = container.createNewAgent("Principal", MyContractNetAgentInitiatior.class.getCanonicalName(), new Object[] {5, true});
////			Thread.sleep(10000);
//			myController.start();
//			
////			String[] bidderNames = new String[] {"Albert", "Benny", "Celia", "Andres", "Eustace", "Fenny"};
////			Vector<AgentController> ctrls = new Vector<AgentController>();			
////			for(String bidderName : bidderNames)
////				ctrls.add(container.createNewAgent(bidderName, DutchAuctionResponderAgent.class.getCanonicalName(), new Object[] {}));
////			
////			AgentController auct = container.createNewAgent("Auctioneer", DutchAuctionInitiatorAgent.class.getCanonicalName(), bidderNames);
////			Thread.sleep(10000);
////			auct.start();
////			for(AgentController ctrl : ctrls)
////				ctrl.start();
//
//		} catch(StaleProxyException e)
//		{
//			e.printStackTrace();
//		}
	}
}
