import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.GlobalConfig
import com.baomidou.mybatisplus.generator.config.OutputFile
import com.baomidou.mybatisplus.generator.config.PackageConfig
import com.baomidou.mybatisplus.generator.config.StrategyConfig
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import org.junit.jupiter.api.Test
import java.util.*


internal class EduStatisticsControllerTest {

    @Test
    fun addCenter() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/guli?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8", "root", "123456")
            .globalConfig { builder: GlobalConfig.Builder ->
                builder.author("LovesAsuna") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .outputDir("E:\\work\\workspace\\OnlineEducation\\service\\service-statistics\\src\\main\\kotlin") // 指定输出目录
            }
            .packageConfig { builder: PackageConfig.Builder ->
                builder.parent("com.hyosakura") // 设置父包名
                    .moduleName("statistics") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\work\\workspace\\OnlineEducation\\service\\service-statistics\\src\\main\\kotlin\\com\\hyosakura\\order\\mapper\\xml")) // 设置mapperXml生成路径
            }
            .strategyConfig { builder: StrategyConfig.Builder ->
                builder.addInclude("statistics_daily") // 设置需要生成的表名
                    .addTablePrefix("t_")
                    .controllerBuilder()
                    .enableRestStyle()
                    .enableHyphenStyle()
                    .build()
                    .entityBuilder()
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .build()
            }
            .execute()
    }
}