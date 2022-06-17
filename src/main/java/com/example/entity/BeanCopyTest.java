package com.example.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author hkh
 * @version 1.0.0
 * @Description bean复制测试
 * @createTime 2022年05月09日 09:27:00
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class BeanCopyTest {
    private String name;
    private String code;
    private Integer status;
}
