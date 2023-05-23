package cn.ctrlcv.eighteen.wechat.controller.couple;

import cn.ctrlcv.eighteen.common.model.ApiResult;
import cn.ctrlcv.eighteen.wechat.controller.couple.vo.CoupleRelationshipVO;
import cn.ctrlcv.eighteen.wechat.service.ICoupleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * Class Name: CoupleRelationshipController
 * Class Description: 情侣关系控制器
 *
 * @author liujm
 * @date 2023-05-03
 */
@RestController
@RequestMapping("/v1/couple/")
public class CoupleRelationshipController {

    @Resource
    private ICoupleService coupleService;

    /**
     * 获取情侣关系信息
     *
     * @param id 情侣关系ID
     * @return {@link CoupleRelationshipVO}
     */
    @GetMapping("/info/{id}")
    public ApiResult<CoupleRelationshipVO> getInfo(@PathVariable Long id) {
        return ApiResult.success(coupleService.getInfo(id));
    }

}
