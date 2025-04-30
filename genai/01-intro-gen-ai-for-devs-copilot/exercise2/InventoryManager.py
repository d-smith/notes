class InventoryManager:
    def __init__(self):
        self.inventory = {}

    def add_item(self, item_id, quantity):
        if item_id in self.inventory:
            self.inventory[item_id] += quantity
        else:
            self.inventory[item_id] = quantity

    def remove_item(self, item_id, quantity):
        if item_id in self.inventory:
            if self.inventory[item_id] >= quantity:
                self.inventory[item_id] -= quantity
            else:
                print("Not enough items in stock.")
        else:
            print("Item not found in inventory.")

    def check_stock(self, item_id):
        return self.inventory.get(item_id, 0)
