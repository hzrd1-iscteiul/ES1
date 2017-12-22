package antiSpamFilter;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class JTests {
	
	

	@Before
	public void initializePaths() {
		AntiSpamFilterManager.getInstance().getGUI().setSpam_Path(System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\spam.log");
		AntiSpamFilterManager.getInstance().getGUI().setHamPath(System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\ham.log");
	}

	
	@Test
	public void testGetInstance() {
		assertNotEquals(AntiSpamFilterManager.getInstance(),null);
	}

	@Test
	public void testGetRulesList() {
		assertNotEquals(AntiSpamFilterManager.getInstance().getRulesList(),null);
	}
	
	@Test
	public void testLoadRules() {
		AntiSpamFilterManager.getInstance().getGUI().setRules_Path(System.getProperty("user.dir") + "\\jUnitTest\\rule.cf");
		AntiSpamFilterManager.getInstance().loadRules();
		AntiSpamFilterManager.getInstance().getGUI().setRules_Path(System.getProperty("user.dir") + "\\AntiSpamConfigurationForProfessionalMailbox\\rule.cf");
		AntiSpamFilterManager.getInstance().loadRules();
	}
	

	@Test
	public void testSaveRules() {
		AntiSpamFilterManager.getInstance().getGUI().save.doClick();
		File manual = new File(AntiSpamFilterManager.getInstance().getGUI().getRules_Path());
		assertTrue(manual.isFile());
	}

	@Test
	public void testAutomatic() {
		AntiSpamFilterManager.getInstance().getGUI().autoButton.doClick();
		AntiSpamFilterManager.getInstance().getGUI().start.doClick();
		assertNotEquals(-1, AntiSpamFilterManager.getInstance().getGUI().getFN());
		assertNotEquals(-1, AntiSpamFilterManager.getInstance().getGUI().getFP());
	}
	
	@Test
	public void testRandomValues() {
		AntiSpamFilterManager.getInstance().getGUI().randomValues.doClick();
		assertNotNull(AntiSpamFilterManager.getInstance().getRulesList());
	}

	@Test
	public void testManual() {
		AntiSpamFilterManager.getInstance().getGUI().setRulesList(AntiSpamFilterManager.getInstance().random_values());
		AntiSpamFilterManager.getInstance().getGUI().manualButton.doClick();
		AntiSpamFilterManager.getInstance().getGUI().start.doClick();
		assertNotEquals(-1, AntiSpamFilterManager.getInstance().getGUI().getFN());
		assertNotEquals(-1, AntiSpamFilterManager.getInstance().getGUI().getFP());
	}

}
