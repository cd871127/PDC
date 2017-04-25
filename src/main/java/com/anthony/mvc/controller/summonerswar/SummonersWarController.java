package com.anthony.mvc.controller.summonerswar;

import com.anthony.mvc.dto.summonerswar.MonsterFightTimeDTO;
import com.anthony.mvc.util.summonerswar.FightNum;
import com.anthony.net.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chend on 2017/4/16.
 */
@RestController
public class SummonersWarController {
    @RequestMapping(value = "test")
    public int calculateFightTimes(MonsterFightTimeDTO monsterFightTimeDTO)
    {
//        monsterFightTimeDTO=new MonsterFightTimeDTO();
//        monsterFightTimeDTO.setCurExp(0);
//        monsterFightTimeDTO.setCurLevel(1);
//        monsterFightTimeDTO.setStar(3);
//        monsterFightTimeDTO.setFightExp(2552);
        int res=FightNum.getFightNum(monsterFightTimeDTO);
        return res;
    }
}