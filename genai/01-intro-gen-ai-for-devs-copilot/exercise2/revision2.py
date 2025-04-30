from threading import Lock
from typing import Dict

# Custom Exceptions
class InventoryError(Exception):
    """Base class for inventory-related exceptions."""
    pass

class NotEnoughStockError(InventoryError):
    """Raised when there's insufficient stock to remove the requested quantity."""
    pass

class ItemNotFoundError(InventoryError):
    """Raised when the specified item is not found in the inventory."""
    pass

class InventoryManager:
    """
    A class to manage an inventory for an e-commerce system.

    Attributes:
        inventory (Dict[str, int]): Maps item IDs to their current quantities.
        lock (Lock): A threading lock to ensure thread-safe operations.
    """

    def __init__(self) -> None:
        self.inventory: Dict[str, int] = {}
        self.lock = Lock()

    def add_item(self, item_id: str, quantity: int) -> None:
        """
        Adds a specified quantity of an item to the inventory.

        Args:
            item_id (str): Unique identifier for the item.
            quantity (int): The positive quantity to add.

        Raises:
            TypeError: If item_id is not a string.
            ValueError: If quantity is not a positive integer.
        """
        if not isinstance(item_id, str):
            raise TypeError("The item_id must be a string.")
        if not isinstance(quantity, int) or quantity <= 0:
            raise ValueError("The quantity must be a positive integer.")

        with self.lock:
            self.inventory[item_id] = self.inventory.get(item_id, 0) + quantity

    def remove_item(self, item_id: str, quantity: int) -> None:
        """
        Removes a specified quantity of an item from the inventory.

        Args:
            item_id (str): Unique identifier for the item.
            quantity (int): The positive quantity to remove.

        Raises:
            TypeError: If item_id is not a string.
            ValueError: If quantity is not a positive integer.
            ItemNotFoundError: If the item_id is not found.
            NotEnoughStockError: If there isn't enough stock to remove the requested quantity.
        """
        if not isinstance(item_id, str):
            raise TypeError("The item_id must be a string.")
        if not isinstance(quantity, int) or quantity <= 0:
            raise ValueError("The quantity must be a positive integer.")

        with self.lock:
            if item_id not in self.inventory:
                raise ItemNotFoundError(f"Item '{item_id}' not found in inventory.")
            if self.inventory[item_id] < quantity:
                raise NotEnoughStockError(
                    f"Insufficient stock for item '{item_id}': available {self.inventory[item_id]}, required {quantity}."
                )
            self.inventory[item_id] -= quantity

            # Optionally remove the item completely if its quantity reaches zero.
            if self.inventory[item_id] == 0:
                del self.inventory[item_id]

    def check_stock(self, item_id: str) -> int:
        """
        Returns the current stock for a given item_id.

        Args:
            item_id (str): Unique identifier for the item.

        Returns:
            int: The current quantity available (0 if not found).

        Raises:
            TypeError: If item_id is not a string.
        """
        if not isinstance(item_id, str):
            raise TypeError("The item_id must be a string.")

        with self.lock:
            return self.inventory.get(item_id, 0)

    def list_low_stock_items(self) -> Dict[str, int]:
        """
        Lists all items with low stock (quantity less than 5).

        Returns:
            Dict[str, int]: A dictionary of item_id and their quantities where the stock is low.
        """
        low_stock_items: Dict[str, int] = {}
        with self.lock:
            for item_id, quantity in self.inventory.items():
                if quantity < 5:
                    low_stock_items[item_id] = quantity
        return low_stock_items
