import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import play.Environment;
import play.Logger;
import play.inject.ApplicationLifecycle;
import akka.actor.ActorSystem;

import com.robosoft.constants.AppConstants;
import com.robosoft.constants.ResponseMessages;
import com.robosoft.service.commons.CommonServices;
import com.robosoft.utils.scheduledJobs.SchedulerThread;

// This creates an `ApplicationStart` object once at start-up.
@Singleton
public class ApplicationStart {

	// Inject the application's Environment upon start-up and register hook(s)
	// for shut-down.
	@Inject
	public ApplicationStart(ApplicationLifecycle lifecycle, Environment environment, ActorSystem system) {
		Logger.info("----Application start----");
		// Shut-down hook
		doPreLoadings();
		startShedulers(system);
		Logger.info("----Application start done----");
		lifecycle.addStopHook(() -> {
			Logger.info("----Application end----");
			return CompletableFuture.completedFuture(null);
		});
		// ...
	}

	private void doPreLoadings() {
		try {
			Logger.info("Loading app settings...");
			AppConstants.appSettings = CommonServices.getInstance().loadAppSettings();
			Logger.info("Loading response messages...");
			ResponseMessages.responseMessages = CommonServices.getInstance().loadResponseMessages();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void startShedulers(ActorSystem system) {
		new Thread(new SchedulerThread(system)).start();
	}

}