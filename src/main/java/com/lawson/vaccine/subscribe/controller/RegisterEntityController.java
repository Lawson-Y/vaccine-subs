package com.lawson.vaccine.subscribe.controller;

import com.lawson.vaccine.subscribe.services.YuemiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lawson
 * @since 2023-08-02
 */
@RestController
public class RegisterEntityController {

    @Autowired
    private YuemiaoService yuemiaoService;

    @GetMapping(path = "/{proxyId}/{uuid}")
    public void wxLoginQr(HttpServletResponse res, @PathVariable(name = "uuid") String uuid) throws IOException {
        String wxQr = this.yuemiaoService.getWxQr(uuid);
        if (wxQr.startsWith("http")) {
            res.sendRedirect(wxQr);
            return;
        }
        res.getWriter().close();
    }

}
