package zvezdo4et.customenchantments;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public abstract class ProcreatorEnchantments extends Enchantment {

    protected final String displayName; // Имя зачарования
    protected final int maxLevel; // Максимальный уровень зачарования
    protected final int minCost; // Минимальная стоимость для уровня 1
    protected final int levelCost; // Прибавка стоимости
    protected final int maxCost; // Разброс стоимости
    protected final int weight; // Редкость (1-10)

    private static final String[] ROMAN = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    protected static String toRoman(int num){
        if (num <= 0 || num >= ROMAN.length){
            return String.valueOf(num);
        }

        return ROMAN[num];
    }

    public ProcreatorEnchantments(@NotNull NamespacedKey key, String displayName, int maxLevel, int minCost, int levelCost, int maxCost, int weight) {
        super(key);
        this.displayName = displayName;
        this.maxLevel = maxLevel;
        this.minCost = minCost;
        this.levelCost = levelCost;
        this.maxCost = maxCost;
        this.weight = weight;
    }

    @Override
    public @NotNull String getName() {
        return getKey().getKey().toUpperCase();
    }
    public String getCustomName() {
        return displayName;
    }
    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel(){
        return 1;
    }

    // Определяет тип предметов, на которые можно наложить зачарования
    @Override
    public abstract @NotNull EnchantmentTarget getItemTarget();

    // Является ли зачарование "сокровищем" (редким)
    @Override
    public abstract boolean isTreasure();

    // Является ли зачарование проклятьем
    @Override
    public abstract boolean isCursed();

    // Можно ли получить зачарование у жителей
    @Override
    public abstract boolean isTradeable();

    // Можно ли обнаружить зачарование в столе зачарований
    @Override
    public abstract boolean isDiscoverable();

    // Возвращает редкость зачарования
    @Override
    public abstract @NotNull EnchantmentRarity getRarity();

    // Возвращает увеличение урона
    @Override
    public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    // Активные слоты экипировки в которых зачарование работает
    @Override
    public abstract @NotNull Set<EquipmentSlot> getActiveSlots();


    // Проверяет конфликты с другими зачарованиями
    @Override
    public abstract boolean conflictsWith(@NotNull Enchantment other);

    // Можно ли зачаровать данный предмет
    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return getItemTarget().includes(item);
    }

    // Рассчитывает минимальную стоимость зачарования для заданного уровня
    public int getMinCost(int level) {
        return minCost + (level - 1) * levelCost;
    }

    // Рассчитывает максимальную стоимость зачарования для заданного уровня
    public int getMaxCost(int level) {
        return this.getMinCost(level) + maxCost;
    }

    // Возвращает редкость зачарования
    public int getWeight() {
        return weight;
    }

    // Возвращает отображаемое имя зачарования с уровнем
    @Override
    public abstract @NotNull Component displayName(int level);



}
