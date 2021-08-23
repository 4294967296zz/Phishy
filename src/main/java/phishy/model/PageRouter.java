package phishy.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageRouter {

    @GetMapping("/")
    public String index() {
        return "pages/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pages/dashboard";
    }

    @GetMapping("/mailform")
    public String mailform() { return "pages/mailform"; }

    @GetMapping("/org")
    public String org() {
        return "pages/org";
    }

    @GetMapping("/project")
    public String project() {
        return "pages/project";
    }

    @GetMapping("/group")
    public String group() { return "pages/group"; }

    @GetMapping("/report")
    public String report() {
        return "pages/report";
    }

    @GetMapping("/status")
    public String status() {
        return "pages/status";
    }

    @GetMapping("/execute")
    public String execute() {
        return "pages/execute";
    }

    @GetMapping("/status_detail")
    public String status_detail() { return "pages/status_detail"; }

    @GetMapping("/sys_setting")
    public String sys_setting() {
        return "pages/sys_setting";
    }

    @GetMapping("/training_setting")
    public String training_setting() {
        return "pages/training_setting";
    }

    @GetMapping("/usergroup")
    public String usergroup() {
        return "pages/usergroup";
    }

    @GetMapping("/result")
    public String result() {
        return "pages/result";
    }

    @GetMapping("/result_detail")
    public String result_detail() {
        return "pages/result_detail";
    }

    @GetMapping("/user")
    public String user() {
        return "pages/tables";
    }

    @GetMapping("/notice")
    public String notice() { return "pages/notice"; }



}
