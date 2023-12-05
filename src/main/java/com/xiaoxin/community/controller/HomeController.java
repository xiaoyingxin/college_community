package com.xiaoxin.community.controller;

import com.xiaoxin.community.entity.DiscussPost;
import com.xiaoxin.community.entity.Page;
import com.xiaoxin.community.entity.User;
import com.xiaoxin.community.service.DiscussPostService;
import com.xiaoxin.community.service.LikeService;
import com.xiaoxin.community.service.UserService;
import com.xiaoxin.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page,
                               @RequestParam(name = "orderMode", defaultValue = "0") int orderMode) {
        // 方法调用之前，SpringMVC会自动实例化Model和Page，并将Page注入Model。
        // 所以，在thymeleaf中可以直接访问Page对象中的数据。
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index?orderMode=" + orderMode);

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit(), orderMode);
        ArrayList<Map<String, Object>> discussPosts = new ArrayList<>();
        for (DiscussPost discussPost : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("post", discussPost);
            User user = userService.findUserById(discussPost.getUserId());
            map.put("user", user);

            long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPost.getId());
            map.put("likeCount", likeCount);
            discussPosts.add(map);
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("orderMode", orderMode);
        // 重构后，不需要再返回page对象了。
        return "/index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/500";
    }

    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String getDeniedPage() {
        return "/error/404";
    }
}
