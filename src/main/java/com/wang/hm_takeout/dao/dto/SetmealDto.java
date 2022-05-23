package com.wang.hm_takeout.dao.dto;

import com.wang.hm_takeout.dao.domain.Setmeal;
import com.wang.hm_takeout.dao.domain.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
