package com.teachain.applet.controller;

import com.teachain.common.result.R;
import com.teachain.store.entity.Store;
import com.teachain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 小程序门店接口
 */
@RestController
@RequestMapping("/api/applet/stores")
@RequiredArgsConstructor
public class AppletStoreController {

    private final StoreService storeService;

    @GetMapping("/nearby")
    public R<List<Store>> nearby(@RequestParam Double longitude,
                                  @RequestParam Double latitude,
                                  @RequestParam(defaultValue = "5000") Integer radius) {
        return R.ok(storeService.list());
    }

    @GetMapping
    public R<List<Store>> list() {
        return R.ok(storeService.list());
    }
}
