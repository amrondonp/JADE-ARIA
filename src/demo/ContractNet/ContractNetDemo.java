package demo.ContractNet;

import java.util.Vector;

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
	public static void main(String[] args) {
		Runtime jade = Runtime.instance();
		Properties mainProps = new ExtendedProperties();
		mainProps.setProperty(Profile.MAIN, "true");
		mainProps.setProperty(Profile.GUI, "true");
		mainProps.setProperty(Profile.MAIN_HOST, "127.0.0.1");
		
		jade.createMainContainer(new ProfileImpl(mainProps));
		ContainerController container = jade.createAgentContainer(new ProfileImpl());
		try
		{
			AgentController myController = container.createNewAgent("Principal", MyContractNetAgentInitiatior.class.getCanonicalName(), new Object[] {5, true});
//			Thread.sleep(10000);
			myController.start();
			
//			String[] bidderNames = new String[] {"Albert", "Benny", "Celia", "Andres", "Eustace", "Fenny"};
//			Vector<AgentController> ctrls = new Vector<AgentController>();			
//			for(String bidderName : bidderNames)
//				ctrls.add(container.createNewAgent(bidderName, DutchAuctionResponderAgent.class.getCanonicalName(), new Object[] {}));
//			
//			AgentController auct = container.createNewAgent("Auctioneer", DutchAuctionInitiatorAgent.class.getCanonicalName(), bidderNames);
//			Thread.sleep(10000);
//			auct.start();
//			for(AgentController ctrl : ctrls)
//				ctrl.start();

		} catch(StaleProxyException e)
		{
			e.printStackTrace();
		}
	}
}
