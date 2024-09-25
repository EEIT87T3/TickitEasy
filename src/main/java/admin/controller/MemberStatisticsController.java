package admin.controller;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import member.service.MemberService;

@Controller
@RequestMapping("/admin")
public class MemberStatisticsController {

    private static final Logger logger = Logger.getLogger(MemberStatisticsController.class.getName());

    @Autowired
    private MemberService memberService;

    // 處理渲染統計頁面的請求
    @GetMapping("/memberStatistics")
    public String showStatisticsPage(Model model) {
        logger.info("Rendering member statistics page");
        return "admin/memberStatistics";
    }

    // 處理獲取統計數據的 AJAX 請求
    @GetMapping("/memberStatistics/data")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getStatisticsData() {
        logger.info("Fetching member statistics data");
        try {
            Map<String, Object> statistics = memberService.getMemberStatistics();
            logger.info("Successfully retrieved member statistics");
            return statistics;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching member statistics", e);
            throw new RuntimeException("Failed to fetch member statistics", e);
        }
    }

    // 異常處理
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleException(RuntimeException e) {
        logger.log(Level.SEVERE, "An error occurred while processing the request", e);
        return Map.of("error", "An unexpected error occurred. Please try again later.");
    }
}