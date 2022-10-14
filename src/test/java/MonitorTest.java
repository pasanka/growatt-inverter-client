import model.ChargingMode;
import model.OutputMode;
import model.SettingsResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Timer;

public class MonitorTest {

    Monitor monitor = new Monitor();

    @Test
    public void testMonitor() throws InterruptedException {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(monitor, 0, 300000);
        Thread.currentThread().join();
    }

    @Test(testName = "[OUTPUT][Energy Saver] Set to SBU")
    public void testOutputChangeToSBU() throws IOException {
        monitor.updateOutputMode(OutputMode.SBU);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SUB")
    public void testOutputChangeToSUB() throws IOException {
        monitor.updateOutputMode(OutputMode.SUB);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SOL")
    public void testOutputChangeToSOL() throws IOException {
        monitor.updateOutputMode(OutputMode.SOL);
    }

    @Test(testName = "[CHARGING][Energy Saver] Set to Solar Only")
    public void testChargingChangeToSolarOnly() throws IOException {
        monitor.updateChargingMode(ChargingMode.SOLAR_ONLY);
    }

    @Test(testName = "[CHARGING][Power-cut Safe] Set to Solar Priority")
    public void testChargingChangeToSolarPriority() throws IOException {
        monitor.updateChargingMode(ChargingMode.SOLAR_PRIORITY);
    }

    @Test
    public void testReadSettings() throws IOException {
        String cookie = monitor.loginToWebService();
        SettingsResponse settings = monitor.readSettings(cookie);
        System.out.println(settings);
    }

    // Add multiple test methods for cronjob to change MODE depending on current battery capacity at different times of the day

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SUB 5PM")
    public void testOutputChangeToSUB5PM() throws IOException {
        updateOutputMode(101);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SUB 4PM")
    public void testOutputChangeToSUB4PM() throws IOException {
        updateOutputMode(90);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SUB 3PM")
    public void testOutputChangeToSUB3PM() throws IOException {
        updateOutputMode(75);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SUB 1PM")
    public void testOutputChangeToSUB1PM() throws IOException {
        updateOutputMode(50);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SUB 11AM")
    public void testOutputChangeToSUB11AM() throws IOException {
        updateOutputMode(35);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SBU 8PM")
    public void testOutputChangeToSBU8PM() throws IOException {
        updateOutputMode(85);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SBU 9PM")
    public void testOutputChangeToSBU9PM() throws IOException {
        updateOutputMode(75);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SBU 10PM")
    public void testOutputChangeToSBU10PM() throws IOException {
        updateOutputMode(65);
    }

    @Test(testName = "[OUTPUT][Power-cut Safe] Set to SBU 11PM")
    public void testOutputChangeToSBU11PM() throws IOException {
        updateOutputMode(35);
    }

    private void updateOutputMode(double expectedBatCapacity) throws IOException {
        String cookie = monitor.loginToWebService();

        double currentCapacity = monitor.readCurrentBatCapacity(cookie);

        if(currentCapacity < expectedBatCapacity) {
            System.out.println("Current battery capacity does not satisfy expected battery capacity : " + expectedBatCapacity);
            monitor.updateOutputMode(OutputMode.SUB, currentCapacity, cookie);
        } else {
            System.out.println("Current battery capacity satisfy expected battery capacity : " + expectedBatCapacity);
            monitor.updateOutputMode(OutputMode.SBU, currentCapacity, cookie);
        }
    }

}