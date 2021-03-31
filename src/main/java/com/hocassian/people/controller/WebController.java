package com.hocassian.people.controller;

import com.hocassian.people.controller.base.BaseController;
import com.hocassian.people.domain.web.ConnectWeb;
import com.hocassian.people.domain.web.PersonWeb;
import com.hocassian.people.service.web.WebService;
import com.hocassian.people.transport.AjaxResult;
import com.hocassian.people.transport.Result;
import org.neo4j.driver.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能描述：
 *
 * @author Hocassian
 * @date 2021-01-16 11:09
 */
@RestController
@RequestMapping("/people/web")
public class WebController extends BaseController {

    @Autowired
    private WebService webService;

    @GetMapping("/map")
    public Result getPersonWebMap() {
        List<Record> list = webService.selectPersonWebMap();
        return getDataResult(list);
    }

    @GetMapping("/list")
    public Result getPersonWebList() {
        List<PersonWeb> list = webService.selectPersonWebList();
        return getDataResult(list);
    }

    @GetMapping(value = "/person/{personWebId}")
    public AjaxResult getPersonWebInfo(@PathVariable("personWebId") String personWebId) {
        return AjaxResult.success(webService.selectPersonWebById(personWebId));
    }

    @PostMapping("/person")
    public AjaxResult addPersonWeb(@RequestBody PersonWeb personWeb) {
        return toAjax(webService.insertPersonWeb(personWeb));
    }

    @PutMapping("/person")
    public AjaxResult editPersonWeb(@RequestBody PersonWeb personWeb) {
        return toAjax(webService.updatePersonWeb(personWeb));
    }

    @DeleteMapping("/person/{personWebId}")
    public AjaxResult removePersonWeb(@PathVariable String personWebId) {
        return toAjax(webService.deletePersonWeb(personWebId));
    }

    @GetMapping(value = "/connect/{connectWebId}")
    public AjaxResult getInfoConnectWeb(@PathVariable("connectWebId") String connectWebId) {
        return AjaxResult.success(webService.selectConnectWebById(connectWebId));
    }

    @PostMapping("/connect")
    public AjaxResult addConnectWeb(@RequestBody ConnectWeb connectWeb) {
        return toAjax(webService.insertConnectWeb(connectWeb));
    }

    @PutMapping("/connect")
    public AjaxResult editConnectWeb(@RequestBody ConnectWeb connectWeb) {
        return toAjax(webService.updateConnectWeb(connectWeb));
    }

    @DeleteMapping("/connect/{connectWebId}")
    public AjaxResult removeConnectWeb(@PathVariable String connectWebId) {
        return toAjax(webService.deleteConnectWeb(connectWebId));
    }
}
