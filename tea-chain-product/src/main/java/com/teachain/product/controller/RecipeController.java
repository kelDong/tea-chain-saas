package com.teachain.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.teachain.common.result.PageResult;
import com.teachain.common.result.R;
import com.teachain.product.entity.Recipe;
import com.teachain.product.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public R<PageResult<Recipe>> page(@RequestParam(defaultValue = "1") long current,
                                      @RequestParam(defaultValue = "10") long size) {
        IPage<Recipe> page = recipeService.page(new Page<>(current, size));
        return R.ok(PageResult.of(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords()));
    }

    @GetMapping("/{id}")
    public R<Recipe> getById(@PathVariable Long id) {
        return R.ok(recipeService.getById(id));
    }

    @PostMapping
    public R<Void> save(@RequestBody Recipe recipe) {
        recipeService.save(recipe);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipe.setId(id);
        recipeService.updateById(recipe);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        recipeService.removeById(id);
        return R.ok();
    }
}
