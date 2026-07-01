package com.teachain.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.order.entity.Cart;
import com.teachain.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public R<PageResult<Cart>> list(@RequestParam(defaultValue = "1") long current,
                                    @RequestParam(defaultValue = "10") long size) {
        Page<Cart> page = cartService.page(new Page<>(current, size), new LambdaQueryWrapper<>());
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<Cart> detail(@PathVariable Long id) {
        return R.ok(cartService.getById(id));
    }

    @PostMapping
    public R<Void> add(@RequestBody Cart cart) {
        cartService.save(cart);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody Cart cart) {
        cart.setId(id);
        cartService.updateById(cart);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable Long id) {
        cartService.removeById(id);
        return R.ok();
    }
}
