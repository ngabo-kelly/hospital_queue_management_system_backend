package hospital.hospital_queue_management_system.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.hospital_queue_management_system.Service.DashBoardService;

@RestController 
@RequestMapping("/api/dashboard")
public class DashBoardController {

    @Autowired 
    private DashBoardService dashboardService;

    @GetMapping ("/summary")
    public Map<String,Long>getDashBoard(){
        return dashboardService.getDashboard();
    }
}
